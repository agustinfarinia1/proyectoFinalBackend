package com.farinia.proyectoFinal.controller;

import com.farinia.proyectoFinal.Handle.ProyectoFinalException;
import com.farinia.proyectoFinal.models.Carrito;
import com.farinia.proyectoFinal.models.ItemCarrito;
import com.farinia.proyectoFinal.models.Orden;
import com.farinia.proyectoFinal.service.Interfaces.CarritoServiceInterfaz;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log4j2
public class CarritoController {

    private final CarritoServiceInterfaz service;

    @GetMapping("/carrito/{email}")
    public Carrito getCarrito(@PathVariable String email){
        log.info("Controller-CrearCarrito");
        return service.getByEmail(email);
    }

    @PostMapping("/carrito/create/{email}/{direccion}")
    public Carrito crearCarrito(@PathVariable String email,@PathVariable String direccion){
        log.info("Controller-CrearCarrito");
        return service.create(email,direccion);
    }

    @PostMapping("/carrito/{email}")
    public Carrito addItem(@PathVariable String email,@RequestBody ItemCarrito item_nuevo) {
        log.info("Controller-AddItemCarrito");
        return service.add(email,item_nuevo);
    }

    @PutMapping("/carrito/{email}/{id_Item}")
    public void updateItem(@PathVariable String email,@PathVariable String id_Item,@RequestBody ItemCarrito item_act) {
        log.info("Controller-UpdateItemCarrito");
        service.update(email,id_Item,item_act);
    }

    @DeleteMapping("/carrito/{email}/{id_item}")
    public void deleteProducto(@PathVariable String email,@PathVariable String id_item) {
        log.info("Controller-deleteItemCarrito");
        service.delete(email,id_item);
    }

    @PostMapping("/carrito/confirmar/{email}")
    public Orden confirmarCompra(@PathVariable String email) throws Exception {
        log.info("Controller-AddItemCarrito");
        return service.confirmarCarrito(email);
    }
}
