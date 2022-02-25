package com.farinia.proyectoFinal.controller;

import com.farinia.proyectoFinal.models.Usuario.UsuarioRequest;
import com.farinia.proyectoFinal.models.Usuario.UsuarioResponse;
import com.farinia.proyectoFinal.service.Interfaces.UsuarioServiceInterfaz;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log4j2
public class UsuarioController {

    private final UsuarioServiceInterfaz service;

    @PostMapping("usuario/login")
    public UsuarioResponse login(@RequestBody UsuarioRequest request) throws Exception {
        log.info("Controller-Login");
        return service.login(request);
    }

    @PostMapping("usuario/register")
    public UsuarioResponse register(@RequestBody UsuarioRequest request) throws Exception {
        log.info("Controller-Register");
        return service.register(request);
    }
}
