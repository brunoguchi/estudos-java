package com.exemplo.estudos_java.application.services;

import com.exemplo.estudos_java.application.dtos.TestForMongoDto;
import com.exemplo.estudos_java.application.exceptions.InternalErrorException;
import com.exemplo.estudos_java.application.exceptions.NotFoundException;
import com.exemplo.estudos_java.application.interfaces.MyHandlerService;
import com.exemplo.estudos_java.domain.models.MongoTests;
import com.exemplo.estudos_java.infra.repository.MyModelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class MyHandlerServiceImpl implements MyHandlerService {

    private static final Logger logger = LoggerFactory.getLogger(MyHandlerServiceImpl.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    private MyModelRepository modelRepository;

    public ResponseEntity<String> handle(TestForMongoDto myDTO) {
        if (myDTO.getIdentify().equals("404"))
            throw new NotFoundException(myDTO.getIdentify());

        if (myDTO.getIdentify().equals("500"))
            throw new InternalErrorException(myDTO.getIdentify());

        var model = new MongoTests();
        model.setIdentify(myDTO.getIdentify());
        model.setNome(myDTO.getName());
        modelRepository.save(model);

        logger.warn("passou pelo sincrono");
        return ResponseEntity.ok("DTO processed and saved!");
    }

    public CompletableFuture<Void> handleAsyncGet() {
        return CompletableFuture.runAsync(() -> {
            logger.warn("passou pelo assincrono GET");
        });
    }

    public CompletableFuture<Void> handleAsyncJoin() {
        return CompletableFuture.runAsync(() -> {
            logger.warn("passou pelo assincrono JOIN");
        });
    }

    public List<MongoTests> getAllModels() {
        return modelRepository.findAll();
    }
}