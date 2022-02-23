package com.farinia.proyectoFinal.service;

import com.farinia.proyectoFinal.models.Producto;
import com.farinia.proyectoFinal.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductoService implements ProductoServiceInterfaz<Producto> {

    private final ProductoRepository repository;

    @Override
    public Producto create(Producto request) {
        log.info("Service-Creacion producto");
        return repository.save(request);
    }

    @Override
    public Producto findByid(String id) {
        Optional<Producto> res = repository.findById(id);
        if(res.isPresent()){
            log.info("Service-Producto existente");
            return res.get();
        }
        return null;
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
