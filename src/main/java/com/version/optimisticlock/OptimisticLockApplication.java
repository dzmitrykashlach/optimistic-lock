package com.version.optimisticlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OptimisticLockApplication implements CommandLineRunner {

    @Autowired
    CityRepository cityRepository;

    public static void main(String[] args) {
        SpringApplication.run(OptimisticLockApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        cityRepository.findAll();
    }

}
