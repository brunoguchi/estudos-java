package com.exemplo.estudos_java;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableMongock
public class EstudosJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstudosJavaApplication.class, args);
    }
}
