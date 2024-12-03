package com.gestion_resguardo.controller;

import com.gestion_resguardo.Exception.ResourceNotFoundException;
import com.gestion_resguardo.model.Cuidador;
import com.gestion_resguardo.repository.ICuidadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")

public class CuidadorController {

    @Autowired
    private ICuidadorRepository icuidadorRepository;

    @GetMapping(path = "/Cuidadores")
    public List<Cuidador> listarCuidadores() {
        return icuidadorRepository.findAll();
    }

    @PostMapping(path = "/Cuidadores")
    public Cuidador CreateCuidador(@RequestBody Cuidador cuidador) {

        return icuidadorRepository.save(cuidador);
    }

    @GetMapping("/Cuidador/{id}")
    public ResponseEntity<Cuidador> listarCuidadorporId(@PathVariable Long id) {
        Cuidador cuidador = icuidadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Cuidador con ese ID no existe: " + id));
        return ResponseEntity.ok(cuidador);
    }

    @PutMapping("/Cuidadores/{id}")
    public  ResponseEntity<Cuidador> actualizarCuidador(@PathVariable Long id, @RequestBody Cuidador cuidadorResquest){
        Cuidador cuidador = icuidadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Cuidador con ese ID no existe: " + id));
        cuidador.setNombre(cuidadorResquest.getNombre());
        cuidador.setTelefono(cuidadorResquest.getTelefono());
        cuidador.setEmail(cuidadorResquest.getEmail());

        Cuidador cuidadorActualizado = icuidadorRepository.save(cuidador);
        return  ResponseEntity.ok(cuidadorActualizado);
    }

    @DeleteMapping("/Cuidadores/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCuidador(@PathVariable Long id){
        Cuidador cuidador = icuidadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Cuidador con ese ID no existe: " + id));

        icuidadorRepository.delete(cuidador);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

}
