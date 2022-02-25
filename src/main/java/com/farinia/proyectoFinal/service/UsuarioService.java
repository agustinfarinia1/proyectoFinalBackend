package com.farinia.proyectoFinal.service;

import com.farinia.proyectoFinal.Handle.ProyectoFinalException;
import com.farinia.proyectoFinal.cache.CacheUsuario;
import com.farinia.proyectoFinal.models.Usuario.UsuarioDocument;
import com.farinia.proyectoFinal.models.Usuario.UsuarioRequest;
import com.farinia.proyectoFinal.models.Usuario.UsuarioResponse;
import com.farinia.proyectoFinal.repository.UsuarioRepository;
import com.farinia.proyectoFinal.security.JwtProvider;
import com.farinia.proyectoFinal.service.Interfaces.UsuarioServiceInterfaz;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UsuarioService implements UsuarioServiceInterfaz {

    private final UsuarioRepository repository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final CacheUsuario cache;
    private final EmailService emailService;

    @Override
    public UsuarioResponse login(UsuarioRequest request) throws Exception {
        log.info("Service-Login");
        UsuarioResponse usuarioRedis = leerRedis(request.getEmail());
        if(usuarioRedis != null){
            log.info("Usuario logueado recientemente");
            return usuarioRedis;
        }
        var UsuarioRepository = getByUsername(request.getUsername());
        if (UsuarioRepository == null || !passwordEncoder.matches(request.getPassword(), UsuarioRepository.getPassword())) {
            throw new ProyectoFinalException("El usuario o el password es inválido");
        }
        var token = jwtProvider.getJWTToken(request.getUsername());
        log.info("Logueo exitoso");
        UsuarioResponse usuarioRespuesta = new UsuarioResponse(UsuarioRepository.getUsername(),UsuarioRepository.getEmail(),token);
        guardarRedis(usuarioRespuesta);
        return usuarioRespuesta;
    }

    @Override
    public UsuarioResponse register(UsuarioRequest request) throws ProyectoFinalException {
        log.info("Service-Register");
        validateUser(request);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        UsuarioDocument usuarioDB = new UsuarioDocument(null,request.getUsername(),request.getPassword(),request.getEmail());
        repository.save(usuarioDB);
        log.info("Registro exitoso");
        emailService.sendEmail("Usuario registrado","Usuario generado exitosamente","Usted genero el usuario "+request.getUsername()+" de forma exitosa!",request.getEmail());
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

    public void guardarRedis(UsuarioResponse request){
        log.info("Guardando usuario en redis");
        cache.save(request.getEmail(),request);
    }

    public UsuarioResponse leerRedis(String email) throws Exception {
        UsuarioResponse user = null;
        log.info("Comprobando redis");
        try{
            user = (UsuarioResponse) cache.recover(email,UsuarioResponse.class);
            if(user != null){
                return user;
            }
        }
        catch (Exception e){
            throw new Exception(e);
        }
        return user;
    }

    public boolean comprobarSesion(String email) {
        if(repository.findUserByEmail(email) != null){
            return true;
        }
        return false;
    }
}
