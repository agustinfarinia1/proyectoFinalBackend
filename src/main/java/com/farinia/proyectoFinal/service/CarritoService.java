package com.farinia.proyectoFinal.service;

import com.farinia.proyectoFinal.models.Carrito;
import com.farinia.proyectoFinal.models.Item;
import com.farinia.proyectoFinal.repository.CarritoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CarritoService implements CarritoServiceInterfaz {

    private final CarritoRepository repository;

    @Override
    public Carrito create(String email,String direccion) {
        log.info("Service-Creacion carrito");
        if(repository.findByEmail(email) == null){
            Carrito c = new Carrito();
            c.setFecha_creacion(LocalDateTime.now().toString());
            c.setEmail(email);
            c.setDireccion(direccion);
            c.setLista(new ArrayList<Item>());
            log.info("Creacion carrito exitosa");
            return repository.save(c);
        }
        else{
            log.error("ERROR:Este usuario ya tiene carrito.");
        }
        return null;
    }

    @Override
    public Carrito add(String email,Item item_nuevo){
        if(item_nuevo.getCantidad() > 0){
            log.info("Service-AddItem");
            Carrito c = repository.findByEmail(email);
            if(c != null){
                Item i = equalsItem(email,item_nuevo.getId());
                if(i == null){
                    c.getLista().add(item_nuevo);
                    return repository.save(c);
                }
                else{
                    log.error("ERROR:Ese item ya se encuentra en el carrito.");
                }
            }
            else{
                log.error("ERROR:El usuario no tiene carrito.");
            }
        }
        else{
            log.error("ERROR:La cantidad de Items es invalida.");
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
    public void update(String email,String id_Item,Item item_actualizado) {
        log.info("Service-UpdateItem");
        Item i = equalsItem(email,id_Item);
        if(i != null){
            Carrito c = repository.findByEmail(email);
            if(c != null){
                item_actualizado.setId(id_Item);
                c.getLista().remove(i);
                c.getLista().add(item_actualizado);
                repository.save(c);
            }
            else{
                log.error("ERROR:El usuario no tiene carrito.");
            }
        }
    }

    @Override
    public void delete(String email,String id_item){
        log.info("Service-deleteItem");
        Item i = equalsItem(email,id_item);
        if(i != null) {
            Carrito c = repository.findByEmail(email);
            if(c != null){
                c.getLista().remove(i);
                repository.save(c);
                log.info("Item eliminado exitosamente");
            }
        }
        else{
            log.error("ERROR:El carrito no contiene dicho item.");
        }
    }

    @Override
    public void clearCarrito(String email){
        log.info("Service-clearCarrito");
        Carrito c = repository.findByEmail(email);
        if(c != null){
            c.getLista().clear();
            repository.save(c);
            log.info("Lista carrito limpia");
        }
        else{
            log.error("ERROR:El usuario no tiene carrito.");
        }
    }

    public Item equalsItem(String email,String id_item) {
        Carrito c = repository.findByEmail(email);
        if(c != null){
            Optional<Item> res = c.getLista().stream().filter(item->item.getId().equals(id_item))
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
