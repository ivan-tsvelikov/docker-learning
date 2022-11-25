package com.ivan.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class VisitCounterImplTest {
    @Mock
    RedisTemplate<String, Integer> redisTemplate;
    @Mock
    ValueOperations<String, Integer> valueOperations;

    @Before
    public void setup() {
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    public void testToHaveAnyTestInFlow() {
        VisitCounter visitCounter = new VisitCounterImpl(redisTemplate);
        visitCounter.count();
        verify(redisTemplate, times(2)).opsForValue();
        verify(valueOperations, times(1)).get(any());
        verify(valueOperations, times(1)).set(any(), anyInt());
    }

    @Test
    public void testToHaveAnyTestInFlowVerify2() {
        VisitCounter visitCounter = new VisitCounterImpl(redisTemplate);
        visitCounter.count();
        verify(redisTemplate, times(0)).opsForCluster();
    }
}