package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CacheServiceImpl implements CacheService {


    @Autowired
    private RedisCacheManager redisCacheManager;

    @Override
    public void delCache(String name, Object key) {
        getChapterCache(name).ifPresent(cache -> {
            if (key == null){
                cache.clear();
            }else {
                cache.evict(key);
            }
        });
    }

    private Optional<Cache> getChapterCache(String name){
        Cache c = redisCacheManager.getCache(name);
        return Optional.ofNullable(c);
    }
}
