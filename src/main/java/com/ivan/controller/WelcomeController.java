package com.ivan.controller;

import com.ivan.service.VisitCounter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WelcomeController {
    private final VisitCounter visitCounter;

    @Autowired
    public WelcomeController(VisitCounter visitCounter) {
        this.visitCounter = visitCounter;
    }

    @GetMapping
    public ResponseEntity<?> makeInitialCall() {
        int numberOfVisits = visitCounter.count();
        return new ResponseEntity<>("Added feature branch. Hi there once again! Number of site visits is: " + numberOfVisits, HttpStatus.OK);
    }

    @GetMapping("/crash")
    public ResponseEntity<?> makeACrash() {
        log.error("killing app");
        System.exit(0);
        return new ResponseEntity<>("Crashed", HttpStatus.OK);
    }
}
