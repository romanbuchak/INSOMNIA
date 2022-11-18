package com.rbvgt.dogcellar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DogCellarApplication {
    public static void main(String[] args) {
        SpringApplication.run(DogCellarApplication.class, args);
    }
}