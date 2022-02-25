package com.farinia.proyectoFinal.repository;

import com.farinia.proyectoFinal.models.Orden;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrdenRepository extends MongoRepository<Orden, String> {
    List<Orden> findByEmail(String email);
}
