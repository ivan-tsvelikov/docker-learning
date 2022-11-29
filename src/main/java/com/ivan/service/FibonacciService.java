package com.ivan.service;

import org.springframework.data.util.Pair;

import java.util.List;

public interface FibonacciService {
    String calculateFibonacci(int val);

    List<Pair<String, String>> getCalculated();

    List<String> getCalculatedFibonaccies();
}
