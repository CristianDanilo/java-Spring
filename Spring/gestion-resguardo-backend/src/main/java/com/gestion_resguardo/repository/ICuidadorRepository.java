package com.gestion_resguardo.repository;

import com.gestion_resguardo.model.Cuidador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICuidadorRepository extends JpaRepository<Cuidador,Long> {
}
