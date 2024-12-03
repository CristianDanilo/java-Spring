package com.gestion_resguardo.repository;

import com.gestion_resguardo.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnimalRepository extends JpaRepository<Animal, Long> {
}
