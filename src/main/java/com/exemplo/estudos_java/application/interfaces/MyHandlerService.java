package com.exemplo.estudos_java.application.interfaces;

import com.exemplo.estudos_java.application.dtos.TestForMongoDto;
import com.exemplo.estudos_java.domain.models.MongoTests;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MyHandlerService {
    ResponseEntity<String> handle(TestForMongoDto myDTO);
    CompletableFuture<Void> handleAsyncGet();
    CompletableFuture<Void> handleAsyncJoin();
    List<MongoTests> getAllModels();
}
