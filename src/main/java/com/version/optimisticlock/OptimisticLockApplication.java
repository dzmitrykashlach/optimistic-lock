package com.version.optimisticlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;

@SpringBootApplication
public class OptimisticLockApplication implements CommandLineRunner {

    @Autowired
    CityRepository cityRepository;

    public static void main(String[] args) {
        SpringApplication.run(OptimisticLockApplication.class, args);
    }

    @Override
    public void run(String... args){
        var executor = Executors.newFixedThreadPool(5);
        for (int i = 1; i < 10; i++) {
            executor.execute(() -> {
                cityRepository.findAll();
                City city = cityRepository.findAll().iterator().next();
                city.setCountryId("89");
                cityRepository.save(city);
                cityRepository.findAll();
            });
        }
    }

}
