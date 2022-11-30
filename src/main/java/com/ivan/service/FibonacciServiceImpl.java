package com.ivan.service;

import com.ivan.entity.Fibonacci;
import com.ivan.repository.FibRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FibonacciServiceImpl implements FibonacciService {
    private final RedisTemplate<String, String> redisStringTemplate;
    private final FibRepository fibRepository;

    @Override
    @Transactional
    public String calculateFibonacci(int val) {
        String value = String.valueOf(val);
        String redisResult = redisStringTemplate.opsForValue().get(value);
        if (redisResult != null) {
            return redisResult;
        } else {
            String result = String.valueOf(fibonacci(val));
            redisStringTemplate.opsForValue().set(value, result);
            Fibonacci fibonacci = new Fibonacci();
            fibonacci.setInd(value);
            fibonacci.setValue(result);
            fibRepository.save(fibonacci);
            return result;
        }
    }

    @Override
    @Transactional
    public List<Pair<String, String>> getCalculated() {
        return fibRepository.findAll()
                .stream()
                .map(fibonacci -> Pair.of(fibonacci.getInd(), fibonacci.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<String> getCalculatedFibonaccies() {
        return fibRepository.findAllIndexes();
    }

    private int fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int prevPrev = 0;
        int prev = 1;
        int result = 0;

        for (int i = 2; i <= n; i++)
        {
            result = prev + prevPrev;
            prevPrev = prev;
            prev = result;
        }
        return result;
    }
}
