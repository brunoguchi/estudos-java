package com.exemplo.estudos_java.application.controllers;

import com.exemplo.estudos_java.application.dtos.TestDto;
import com.exemplo.estudos_java.application.dtos.TestForMongoDto;
import com.exemplo.estudos_java.application.dtos.TestMessageDto;
import com.exemplo.estudos_java.application.interfaces.MyHandlerService;
import com.exemplo.estudos_java.application.services.MessageSenderService;
import com.exemplo.estudos_java.application.services.MyCacheService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TestController {

    @Autowired
    private MyHandlerService handlerService;

    @Autowired
    private MyCacheService cacheService;

    @Autowired
    private MessageSenderService messageSenderService;

    @PostMapping("/send-message")
    public String sendMessage(@RequestBody TestMessageDto dto) {
        messageSenderService.sendMessage(dto.getQueueName(), dto.getMessage());
        return "Mensagem enviada para a fila: " + dto.getQueueName();
    }

    @PostMapping("/save")
    public ResponseEntity<TestDto> sendMessage(@RequestBody @Valid TestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping("/save-mongodb")
    public ResponseEntity<String> saveDto(@RequestBody TestForMongoDto testForMongoDto) {
        try {
            handlerService.handleAsyncGet().get(); // Async GET precisa estar encapsulado por try/catch pois emite exceções
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        handlerService.handleAsyncJoin().join(); // Async JOIN é igual o GET porém não emite exceções
        return handlerService.handle(testForMongoDto);
    }

    @GetMapping("/get-all")
    public String getAllFromMongo() {
        Optional<String> cachedModels = cacheService.getModelsFromCache();

        if (cachedModels.isPresent()) {
            return cachedModels.get().trim();
        }

        var models = handlerService.getAllModels();
        cacheService.cacheModels(models.getFirst().getIdentify(), 60);
        return models.getFirst().getIdentify();
    }
}
