package com.farinia.proyectoFinal.repository;

import com.farinia.proyectoFinal.models.Usuario.UsuarioDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<UsuarioDocument, String> {
    UsuarioDocument findUserByUsername(String username);
    UsuarioDocument findUserByEmail(String username);
}
