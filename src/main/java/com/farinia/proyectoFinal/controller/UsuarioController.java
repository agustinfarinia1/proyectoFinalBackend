package com.farinia.proyectoFinal.controller;

import com.farinia.proyectoFinal.models.Usuario.UsuarioRequest;
import com.farinia.proyectoFinal.models.Usuario.UsuarioResponse;
import com.farinia.proyectoFinal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsuarioController {

    private final UserService service;

    @PostMapping("usuario/login")
    public UsuarioResponse login(@RequestBody UsuarioRequest request) throws Exception {
        return service.login(request);
    }

    @PostMapping("usuario/register")
    public UsuarioResponse register(@RequestBody UsuarioRequest request) throws Exception {
        log.info("-----------------");
        log.info("INICIO REGISTRO");
        log.info("-----------------");
        log.info(String.valueOf(request));
        log.info("-----------------");
        return service.register(request);
    }

}
