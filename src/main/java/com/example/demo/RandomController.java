package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
public class RandomController implements RandomControllerApi {

    @Override
    public ResponseEntity<Integer> getRandomNumber() {
        int value = ThreadLocalRandom.current().nextInt();
        return ResponseEntity.ok(value);
    }
}