package com.farinia.proyectoFinal.service;

import com.farinia.proyectoFinal.Handle.ProyectoFinalException;
import com.farinia.proyectoFinal.models.Usuario.UsuarioDocument;
import com.farinia.proyectoFinal.models.Usuario.UsuarioRequest;
import com.farinia.proyectoFinal.models.Usuario.UsuarioResponse;
import com.farinia.proyectoFinal.repository.UsuarioRepository;
import com.farinia.proyectoFinal.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsuarioRepository repository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioResponse login(UsuarioRequest request) throws ProyectoFinalException {

        var UsuarioRepository = getByUsername(request.getUsername());

        if (UsuarioRepository == null || !passwordEncoder.matches(request.getPassword(), UsuarioRepository.getPassword())) {
            throw new ProyectoFinalException("El usuario o el password es inválido");
        }
        var token = jwtProvider.getJWTToken(request.getUsername());
        return new UsuarioResponse(UsuarioRepository.getUsername(),UsuarioRepository.getEmail(),token);
    }

    @Override
    public UsuarioResponse register(UsuarioRequest request) throws ProyectoFinalException {
        validateUser(request);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        UsuarioDocument usuarioDB = new UsuarioDocument(null,request.getUsername(),request.getPassword(),request.getEmail());
        repository.save(usuarioDB);
        return new UsuarioResponse(usuarioDB.getUsername(),usuarioDB.getEmail(),null);
    }

    void validateUser(UsuarioRequest request) throws ProyectoFinalException {
        var user = getByUsername(request.getUsername());
        if (user != null) {
            throw new ProyectoFinalException("El usuario ya existe");
        }
        user = repository.findUserByEmail(request.getEmail());
        if (user != null) {
            throw new ProyectoFinalException("El correo ya se encuentra registrado");
        }
    }

    private UsuarioDocument getByUsername(String username) {
        return repository.findUserByUsername(username);
    }
}
