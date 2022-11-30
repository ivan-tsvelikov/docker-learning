package com.ivan.controller;

import com.ivan.frontend.form.FibonacciInput;
import com.ivan.service.FibonacciService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class FibonacciController {
    private final FibonacciService fibonacciService;

    @GetMapping
    public String makeInitialCall(Model model) {
        model.addAttribute("fibonacciInput", new FibonacciInput());
        return "index";
    }

    @PostMapping("/calculate")
    public String calculateFibonacciAtIndex(Model model, @ModelAttribute FibonacciInput fibonacciInput) {
        if (fibonacciInput.getIndex() > 120) {
            log.error("Index to calculate is too high!");
            return "error";
        }
        log.info("Received fibonacci input {}", fibonacciInput.getIndex());
        fibonacciService.calculateFibonacci(fibonacciInput.getIndex());
        model.addAttribute("indicies", fibonacciService.getCalculatedFibonaccies());
        model.addAttribute("calculated", fibonacciService.getCalculated());
        return "calculate";
    }
}
