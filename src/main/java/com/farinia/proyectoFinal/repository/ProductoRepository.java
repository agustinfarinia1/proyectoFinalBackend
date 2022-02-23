package com.farinia.proyectoFinal.repository;

import com.farinia.proyectoFinal.models.Carrito;
import com.farinia.proyectoFinal.models.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, String> {
    Carrito findByCategoria(String categoria);
}
