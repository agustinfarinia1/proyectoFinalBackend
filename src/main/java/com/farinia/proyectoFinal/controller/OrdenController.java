package com.farinia.proyectoFinal.controller;

import com.farinia.proyectoFinal.models.Carrito;
import com.farinia.proyectoFinal.models.Orden;
import com.farinia.proyectoFinal.service.Interfaces.CarritoServiceInterfaz;
import com.farinia.proyectoFinal.service.Interfaces.OrdenServiceInterfaz;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log4j2
public class OrdenController {

    private final OrdenServiceInterfaz service;

    @GetMapping("/orden/{email}")
    public List<Orden> getOrdenes(@PathVariable String email){
        log.info("Controller-GetOrdenes");
        return service.getByEmail(email);
    }
}
