package com.farinia.proyectoFinal.service;

import com.farinia.proyectoFinal.Handle.ProyectoFinalException;
import com.farinia.proyectoFinal.cache.CacheUsuario;
import com.farinia.proyectoFinal.models.*;
import com.farinia.proyectoFinal.models.Usuario.UsuarioRequest;
import com.farinia.proyectoFinal.repository.CarritoRepository;
import com.farinia.proyectoFinal.repository.UsuarioRepository;
import com.farinia.proyectoFinal.service.Interfaces.CarritoServiceInterfaz;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CarritoService implements CarritoServiceInterfaz {

    private final CarritoRepository repository;
    private final OrdenService ordenService;
    private final ProductoService productoService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Override
    public Carrito create(String email,String direccion) {
        log.info("Service-Creacion carrito");
        if(repository.findByEmail(email) == null){
            Carrito c = new Carrito();
            c.setFecha_creacion(LocalDateTime.now().toString());
            c.setEmail(email);
            c.setDireccion(direccion);
            c.setLista(new ArrayList<ItemCarrito>());
            log.info("Creacion carrito exitosa");
            return repository.save(c);
        }
        else{
            log.error("ERROR:Este usuario ya tiene carrito.");
        }
        return null;
    }

    @Override
    public Carrito add(String email, ItemCarrito item_nuevo){
        if(usuarioService.comprobarSesion(email)) {
            if (item_nuevo.getCantidad() > 0) {
                log.info("Service-AddItem");
                Carrito c = repository.findByEmail(email);
                if (c != null) {
                    ItemCarrito i = equalsItem(email, item_nuevo.getId());
                    if (i == null) {
                        c.getLista().add(item_nuevo);
                        return repository.save(c);
                    } else {
                        log.error("ERROR:Ese item ya se encuentra en el carrito.");
                    }
                } else {
                    log.error("ERROR:El usuario no tiene carrito.");
                }
            } else {
                log.error("ERROR:La cantidad de Items es invalida.");
            }
        }
        else{
            log.error("ERROR:Email invalido.");
        }
        return null;
    }
    @Override
    public Carrito getByEmail(String email) {
        Carrito res = repository.findByEmail(email);
        if(res != null){
            log.info("Service-Carrito existente");
            return res;
        }
        else{
            log.error("ERROR:El usuario no tiene carrito.");
        }
        return null;
    }

    @Override
    public void update(String email, String id_Item, ItemCarrito item_actualizado) {
        log.info("Service-UpdateItem");
        if(usuarioService.comprobarSesion(email)) {
            ItemCarrito i = equalsItem(email, id_Item);
            if (i != null) {
                Carrito c = repository.findByEmail(email);
                if (c != null) {
                    item_actualizado.setId(id_Item);
                    c.getLista().remove(i);
                    c.getLista().add(item_actualizado);
                    repository.save(c);
                } else {
                    log.error("ERROR:El usuario no tiene carrito.");
                }
            }
        }
        else{
            log.error("ERROR:Email invalido.");
        }
    }

    @Override
    public void delete(String email,String id_item){
        log.info("Service-deleteItem");
        if(usuarioService.comprobarSesion(email)) {
            ItemCarrito i = equalsItem(email, id_item);
            if (i != null) {
                Carrito c = repository.findByEmail(email);
                if (c != null) {
                    c.getLista().remove(i);
                    repository.save(c);
                    log.info("Item eliminado exitosamente");
                }
            } else {
                log.error("ERROR:El carrito no contiene dicho item.");
            }
        }
        else{
            log.error("ERROR:Email invalido.");
        }
    }

    @Override
    public void clearCarrito(String email){
        log.info("Service-clearCarrito");
        if(usuarioService.comprobarSesion(email)) {
            Carrito c = repository.findByEmail(email);
            if (c != null) {
                c.getLista().clear();
                repository.save(c);
                log.info("Lista carrito limpia");
            } else {
                log.error("ERROR:El usuario no tiene carrito.");
            }
        }
        else{
            log.error("ERROR:Email invalido");
        }
    }

    @Override
    public Orden confirmarCarrito(String email) throws Exception {
        Orden orden = null;
        log.info("Service-Confirmar carrito");
        if(usuarioService.comprobarSesion(email)){
            Carrito c = repository.findByEmail(email);
            if(c != null){
                if(c.getLista().size() > 0){
                    log.info("Confirmando compra");
                    orden = ordenService.create(obtenerListaOrden(c.getLista()),email);
                    emailService.sendEmail("Compra Realizada","Compra Realizada con exito",orden.getLista().toString(),email);
                    clearCarrito(email);
                }
                else {
                    log.error("ERROR:Carrito Vacio");
                }
            }
        }
        else {
            log.error("ERROR:Email invalido");
        }
        return orden;
    }

    public List<ItemOrden> obtenerListaOrden(List<ItemCarrito> listaCarrito){
        List<ItemOrden> listaOrden = new ArrayList<>();
        for (ItemCarrito item : listaCarrito)
        {
            Producto p = productoService.findByid(item.getId());
            if(p != null){
                ItemOrden orden = new ItemOrden(p,item.getCantidad());
                listaOrden.add(orden);
            }
        }
        return listaOrden;
    }

    public ItemCarrito equalsItem(String email, String id_item) {
        Carrito c = repository.findByEmail(email);
        if(c != null){
            Optional<ItemCarrito> res = c.getLista().stream().filter(item->item.getId().equals(id_item))
                    .findFirst();
            if(res.isPresent()){
                return res.get();
            }
            else{
                log.error("ERROR:El carrito no contiene dicho item.");
            }
        }
        else{
            log.error("ERROR:El usuario no tiene carrito.");
        }
        return  null;
    }
}
