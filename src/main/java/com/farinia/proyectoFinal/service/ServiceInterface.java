package com.farinia.proyectoFinal.service;

import java.util.List;

public interface ServiceInterface<T> {
    T create(T request);
    T findByid(String id);
    void update(String id, T request);
    List<T> searchAll();
    void delete(String id);
}
