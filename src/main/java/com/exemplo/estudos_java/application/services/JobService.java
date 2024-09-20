package com.exemplo.estudos_java.application.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class JobService {
    private static final Logger logger = LoggerFactory.getLogger(JobService.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private ScheduledExecutorService scheduler;

    @Value("${myapp.timer.interval}")
    private long interval;

    @PostConstruct
    public void start() {
        System.out.println("Serviço iniciado com timer de " + interval + " milissegundos");

        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::customMethod, 0, interval, TimeUnit.MILLISECONDS);
    }

    @PreDestroy
    public void stop() {
        System.out.println("Serviço interrompido");

        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }

    public void customMethod() {
        String currentTime = LocalDateTime.now().format(formatter);
        logger.info("Método customizado executado às: {}", currentTime);
    }
}
