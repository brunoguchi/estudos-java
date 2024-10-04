package com.exemplo.estudos_java;

import io.mongock.runner.springboot.EnableMongock;
import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

@SpringBootApplication
@EnableMongock
public class EstudosJavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EstudosJavaApplication.class, args);
    }
}
