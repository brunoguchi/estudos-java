package com.exemplo.estudos_java.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class MyCacheService {

    private static final String CACHE_KEY = "my_models";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public Optional<String> getModelsFromCache() {
        String cachedModels = redisTemplate.opsForValue().get(CACHE_KEY);
        return Optional.ofNullable(cachedModels);
    }

    public void cacheModels(String models, int timer) {
        redisTemplate.opsForValue().set(CACHE_KEY, models, timer, TimeUnit.SECONDS);
    }
}
