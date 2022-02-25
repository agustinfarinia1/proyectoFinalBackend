package com.farinia.proyectoFinal.service.Interfaces;

import com.farinia.proyectoFinal.models.Producto;

import java.util.List;

public interface ProductoServiceInterfaz {
    Producto create(Producto request);
    Producto findByid(String id);
    List<Producto> findByCategoria(String categoria);
    void update(String id, Producto request);
    List<Producto> searchAll();
    void delete(String id);
}
