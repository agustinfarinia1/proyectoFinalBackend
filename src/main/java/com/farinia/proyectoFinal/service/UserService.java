package com.farinia.proyectoFinal.service;

import com.farinia.proyectoFinal.models.Usuario.UsuarioRequest;
import com.farinia.proyectoFinal.models.Usuario.UsuarioResponse;

public interface UserService {
    UsuarioResponse login(UsuarioRequest request) throws Exception;
    UsuarioResponse register(UsuarioRequest request) throws Exception;
}
