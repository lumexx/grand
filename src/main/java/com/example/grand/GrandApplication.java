package com.example.grand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GrandApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrandApplication.class, args);
    }

}
