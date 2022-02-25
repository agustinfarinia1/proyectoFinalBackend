package com.farinia.proyectoFinal.service;

import com.farinia.proyectoFinal.models.Producto;
import com.farinia.proyectoFinal.repository.ProductoRepository;
import com.farinia.proyectoFinal.service.Interfaces.ProductoServiceInterfaz;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductoService implements ProductoServiceInterfaz {

    private final ProductoRepository repository;

    @Override
    public Producto create(Producto request) {
        log.info("Service-Creacion producto");
        return repository.save(request);
    }

    @Override
    public Producto findByid(String id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Producto> findByCategoria(String categoria) {
        return repository.findByCategoria(categoria);
    }

    @Override
    public void update(String id, Producto request) {
        if(findByid(id) != null){
            request.setId(id);
            repository.save(request);
            log.info("Service-Actualizacion exitosa");
        }
    }

    @Override
    public List<Producto> searchAll() {
        return repository.findAll();
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
