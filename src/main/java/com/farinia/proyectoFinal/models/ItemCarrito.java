package com.farinia.proyectoFinal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCarrito {
    private String id;
    private int cantidad;

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ItemCarrito)) {
            return false;
        }
        ItemCarrito c = (ItemCarrito) o;
        return c.getId().equals(this.getId());
    }
}


