package com.farinia.proyectoFinal.service;

import com.farinia.proyectoFinal.models.ItemOrden;
import com.farinia.proyectoFinal.models.Orden;
import com.farinia.proyectoFinal.repository.OrdenRepository;
import com.farinia.proyectoFinal.service.Interfaces.OrdenServiceInterfaz;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrdenService implements OrdenServiceInterfaz {

    private final OrdenRepository repository;

    @Override
    public Orden create(List<ItemOrden> lista, String email) {
        return repository.save(new Orden(null,lista, LocalDateTime.now().toString(),"entregado",email));
    }

    @Override
    public List<Orden> getByEmail(String email) {
        return repository.findByEmail(email);
    }
}
