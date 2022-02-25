package com.farinia.proyectoFinal.service.Interfaces;

import com.farinia.proyectoFinal.Handle.ProyectoFinalException;
import com.farinia.proyectoFinal.models.Carrito;
import com.farinia.proyectoFinal.models.ItemCarrito;
import com.farinia.proyectoFinal.models.Orden;

public interface CarritoServiceInterfaz {
    Carrito create(String email,String direccion);
    Carrito add(String email, ItemCarrito item_nuevo);
    void update(String email, String id_Item, ItemCarrito item_actualizado);
    void delete(String email,String id_item);
    void clearCarrito(String email);
    Orden confirmarCarrito(String email) throws Exception;
    Carrito getByEmail(String email);
}
