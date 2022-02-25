package com.farinia.proyectoFinal.cache;

public interface CacheUsuarioInterfaz<T> {

    T save(String key, T data);
    T recover(String key, Class<T> classValue);
}
