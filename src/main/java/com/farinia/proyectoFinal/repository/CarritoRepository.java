package com.farinia.proyectoFinal.repository;

import com.farinia.proyectoFinal.models.Carrito;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarritoRepository extends MongoRepository<Carrito, String> {
    Carrito findByEmail(String email);
}
