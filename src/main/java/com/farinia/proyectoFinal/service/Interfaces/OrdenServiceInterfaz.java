package com.farinia.proyectoFinal.service.Interfaces;

import com.farinia.proyectoFinal.models.ItemOrden;
import com.farinia.proyectoFinal.models.Orden;

import java.util.List;

public interface OrdenServiceInterfaz {
    Orden create(List<ItemOrden> lista, String email);
    List<Orden> getByEmail(String email);
}
