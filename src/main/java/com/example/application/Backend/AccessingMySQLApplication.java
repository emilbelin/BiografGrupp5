package com.example.application.Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"com.example.application.Backend"})
@SpringBootApplication
public class AccessingMySQLApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessingMySQLApplication.class, args);
    }

}