package com.farinia.proyectoFinal.cache;

import com.farinia.proyectoFinal.config.ApplicationProperties;
import com.farinia.proyectoFinal.utils.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheUsuario<T> implements CacheUsuarioInterfaz<T> {

    private final RedisTemplate<String, T> redisTemplate;
    private final ApplicationProperties properties;
    private HashOperations<String, String, String> hashOperations;
    private final ObjectMapper mapper;

    @PostConstruct
    void setHashOperations() {
        hashOperations = this.redisTemplate.opsForHash();
        this.redisTemplate.expire(Constants.KEY_REDIS, Duration.ofMillis(properties.getTimeOfLife()));
    }

    @Override
    public T save(String key, T data) {
        try {
            hashOperations.put(Constants.KEY_REDIS, key, serializeItem(data));
            return data;
        } catch (JsonProcessingException e) {
            log.error("Error converting message to string", e);
        }
        return data;
    }

    @Override
    public T recover(String key, Class<T> classValue) {
        try {
            var item = hashOperations.get(Constants.KEY_REDIS, key);
            if (item == null) return null;
            return deserializeItem(item, classValue);
        } catch (JsonProcessingException e) {
            log.error("Error deserializing user", e);
        }
        return null;
    }

    private String serializeItem(T item) throws JsonProcessingException {
        var serializeItem = mapper.writeValueAsString(item);
        log.info("Usuario en forma String: {}", serializeItem);
        return serializeItem;
    }

    private T deserializeItem(String jsonInput, Class<T> classValue) throws JsonProcessingException {
        return mapper.readValue(jsonInput, classValue);
    }



}
