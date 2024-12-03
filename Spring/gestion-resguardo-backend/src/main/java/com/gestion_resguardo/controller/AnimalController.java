package com.gestion_resguardo.controller;

import com.gestion_resguardo.Exception.ResourceNotFoundException;
import com.gestion_resguardo.model.Animal;
import com.gestion_resguardo.model.Cuidador;
import com.gestion_resguardo.repository.IAnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")

public class AnimalController {
    @Autowired
    private IAnimalRepository iAnimalRepository;

    @GetMapping(path = "/Animales")
    public List<Animal> listarAnimales(){ return iAnimalRepository.findAll();}

    @PostMapping(path = "/Animales")
    public Animal CreateAnimal(@RequestBody Animal animal){
        return iAnimalRepository.save(animal);
    }

    @GetMapping(path = "/Animales/{id}")
    public ResponseEntity<Animal> listarAnimalesId(@PathVariable Long id){
        Animal animal = iAnimalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("El Animal con ese ID no existe: " + id));
        return  ResponseEntity.ok(animal);
    }

    @PutMapping(path = "/Animales/{id}")
    public  ResponseEntity<Animal> actualizarAnimal(@PathVariable Long id, @RequestBody Animal animalResquest){
        Animal animal = iAnimalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("EL Animal con ese ID no existe: " + id));
        animal.setNombre(animalResquest.getNombre());
        animal.setTipo(animalResquest.getTipo());
        animal.setEdad(animalResquest.getEdad());
        animal.setResguardo(animalResquest.getResguardo());

        Animal animalActualizado = iAnimalRepository.save(animal);
        return ResponseEntity.ok(animalActualizado);
    }

    @DeleteMapping(path = "/Animales/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarAnimal(@PathVariable Long id){
        Animal animal = iAnimalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Animal con ese ID no existe: " +id));

        iAnimalRepository.delete(animal);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);

    }
}
