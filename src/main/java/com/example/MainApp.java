package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example")
public class MainApp {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainApp.class)) {
            RedisService redisService = context.getBean(RedisService.class);

            // Set a value in Redis
            redisService.setValue("exampleKey", "exampleValue");

            // Get the value from Redis
            String value = redisService.getValue("exampleKey");
            System.out.println("Value from Redis: " + value);
        }
    }
}
