package com.farinia.proyectoFinal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("carrito")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Carrito {
    private String id;
    private String email;
    private List<ItemCarrito> lista;
    private String fecha_creacion;
    private String direccion;
}
