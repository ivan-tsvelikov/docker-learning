package com.ivan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class VisitCounterImpl implements VisitCounter {
    private static final String KEY_PREFIX = "site::visits";
    private final RedisTemplate<String, Integer> redisTemplate;


    @Autowired
    public VisitCounterImpl(RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public int count() {
        Integer integer = redisTemplate.opsForValue().get(KEY_PREFIX);
        if (integer == null) {
            integer = 1;
            redisTemplate.opsForValue().set(KEY_PREFIX, integer);
        } else {
            integer = integer + 1;
            redisTemplate.opsForValue().set(KEY_PREFIX, integer);
        }
        return integer;
    }
}
