package com.farinia.proyectoFinal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemOrden {
    private Producto producto;
    private int cantidad;

    @Override
    public String toString() {
        return "producto=" + producto.getNombre() + " X " + cantidad+" = " + producto.getPrecio()*cantidad;
    }
}
