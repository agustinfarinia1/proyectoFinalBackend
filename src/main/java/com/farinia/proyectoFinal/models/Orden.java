package com.farinia.proyectoFinal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("ordenes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orden {
    private String id;
    private List<ItemOrden> lista;
    private String fecha_creacion;
    private String estado;
    private String email;


    public String listar(){
        StringBuilder stringBuilder = new StringBuilder();
        for (ItemOrden item: getLista()) {
            stringBuilder.append(item.toString());
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return listar();
    }
}
