package com.farinia.proyectoFinal.service;

import com.farinia.proyectoFinal.models.Carrito;
import com.farinia.proyectoFinal.models.Item;

import java.util.List;

public interface CarritoServiceInterfaz {
    Carrito create(String email,String direccion);
    Carrito add(String email,Item item_nuevo);
    void update(String email,String id_Item,Item item_actualizado);
    void delete(String email,String id_item);
    Carrito getByEmail(String email);
    void clearCarrito(String email);
}
