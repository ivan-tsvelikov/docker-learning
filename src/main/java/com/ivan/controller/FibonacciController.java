package com.ivan.controller;

import com.ivan.frontend.form.FibonacciInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
public class FibonacciController {

    @GetMapping
    public String makeInitialCall(Model model) {
        model.addAttribute("fibonacciInput", new FibonacciInput());
        return "index";
    }

    @PostMapping("/calculate")
    public String calculateFibonacciAtIndex(Model model, @ModelAttribute FibonacciInput fibonacciInput) {
        log.info("Received fibonacci input {}", fibonacciInput.getIndex());
        //move to postgres
        List<Integer> fibonacciValues = new ArrayList<>(Arrays.asList(1, 4, 6));
        model.addAttribute("indicies", fibonacciValues);

        //move to redis
        List<Pair<Integer, Integer>> calculatedValues = new ArrayList<>();
        Pair<Integer, Integer> one = Pair.of(1, 5);
        Pair<Integer, Integer> two = Pair.of(5, 49);
        Pair<Integer, Integer> three = Pair.of(12, 495);
        calculatedValues.add(one);
        calculatedValues.add(two);
        calculatedValues.add(three);
        model.addAttribute("calculated", calculatedValues);

        return "index";
    }
}
