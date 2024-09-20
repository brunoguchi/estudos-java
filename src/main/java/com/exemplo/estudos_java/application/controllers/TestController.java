package com.exemplo.estudos_java.application.controllers;

import com.exemplo.estudos_java.application.dtos.TestDto;
import com.exemplo.estudos_java.application.dtos.TestForMongoDto;
import com.exemplo.estudos_java.application.services.MyCacheService;
import com.exemplo.estudos_java.application.services.MyHandlerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TestController {

    @Autowired
    private MyHandlerService handlerService;

    @Autowired
    private MyCacheService cacheService;

    @PostMapping("/save")
    public ResponseEntity<TestDto> sendMessage(@RequestBody @Valid TestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping("/save-mongodb")
    public String saveDto(@RequestBody TestForMongoDto testForMongoDto) {
        handlerService.handle(testForMongoDto);
        return "DTO processed and saved!";
    }

    @GetMapping("/get-all")
    public String getAllFromMongo() {
        try {
            Optional<String> cachedModels = cacheService.getModelsFromCache();

            if (cachedModels.isPresent()) {
                return cachedModels.get().trim();
            }

            var models = handlerService.getAllModels();
            cacheService.cacheModels(models.getFirst().getIdentify(), 60);
            return models.getFirst().getIdentify();
        } catch (Exception e) {
            return null;
        }
    }
}
