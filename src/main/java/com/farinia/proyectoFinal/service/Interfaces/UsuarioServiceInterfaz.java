package com.farinia.proyectoFinal.service.Interfaces;

import com.farinia.proyectoFinal.models.Usuario.UsuarioRequest;
import com.farinia.proyectoFinal.models.Usuario.UsuarioResponse;

public interface UsuarioServiceInterfaz {
    UsuarioResponse login(UsuarioRequest request) throws Exception;
    UsuarioResponse register(UsuarioRequest request) throws Exception;
}
