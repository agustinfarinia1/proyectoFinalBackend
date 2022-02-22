package com.farinia.proyectoFinal.controller;

import com.farinia.proyectoFinal.models.Producto;
import com.farinia.proyectoFinal.service.ProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log4j2
public class ProductoController {

    private final ProductoService service;

    @GetMapping("/producto")
    public List<Producto> getProductos(){
        log.info("Controller-GetProductos");
        return service.searchAll();
    }

    @GetMapping("/producto/{id}")
    public Producto getProductos(@PathVariable String id){
        log.info("Controller-GetProductoById");
        return service.findByid(id);
    }

    @PostMapping("/producto")
    public Producto createProducto(@RequestBody Producto producto_act) {
        log.info("Controller-createProducto");
        return service.create(producto_act);
    }

    @PutMapping("/producto/{id}")
    public void updateProducto(@PathVariable String id,@RequestBody Producto producto_act) {
        log.info("Controller-updateProducto");
        service.update(id,producto_act);
    }

    @DeleteMapping("/producto/{id}")
    public void deleteProducto(@PathVariable String id) {
        log.info("Controller-deleteProducto");
        service.delete(id);
    }
}
