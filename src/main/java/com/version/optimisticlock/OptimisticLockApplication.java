package com.version.optimisticlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;
import java.util.concurrent.Executors;
/*
https://vladmihalcea.com/optimistic-vs-pessimistic-locking/
 */
@SpringBootApplication
public class OptimisticLockApplication implements CommandLineRunner {

    @Autowired
    CityRepository cityRepository;

    public static void main(String[] args) {
        SpringApplication.run(OptimisticLockApplication.class, args);
    }

    @Override
    public void run(String... args) {
        var executor = Executors.newFixedThreadPool(2);
        Random random = new Random();
        executor.execute(() -> {
            City city = cityRepository.findById(1).orElseThrow();
            city.setCity(Long.toString(random.nextInt(32000)));

            cityRepository.save(city);
        });
        executor.execute(() -> {
            City city = cityRepository.findById(1).orElseThrow();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            city.setCity(Long.toString(random.nextInt(32000)));
//            ObjectOptimisticLockingFailureException is thrown
            cityRepository.save(city);
        });
    }


}
