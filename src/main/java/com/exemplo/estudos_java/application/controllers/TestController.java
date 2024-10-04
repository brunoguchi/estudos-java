package com.exemplo.estudos_java.application.controllers;

import com.exemplo.estudos_java.application.dtos.*;
import com.exemplo.estudos_java.application.interfaces.MyHandlerService;
import com.exemplo.estudos_java.application.services.MessageSenderService;
import com.exemplo.estudos_java.application.services.MyCacheService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@EnableMethodSecurity
@AllArgsConstructor
public class TestController {
    private final MyHandlerService handlerService;
    private final MyCacheService cacheService;
    private final MessageSenderService messageSenderService;

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

    @PreAuthorize("@securityUtil.hasConsultantRole()")
    @GetMapping("/get-test-cosultant-role")
    public ResponseEntity<String> getTestConsultantRole() {
        return ResponseEntity.ok("deu bom no ROLE_CONSULTANT");
    }

    @PreAuthorize("@securityUtil.hasUserRole()")
    @GetMapping("/get-test-user-role")
    public ResponseEntity<String> getTestUserRole() {
        return ResponseEntity.ok("deu bom ROLE_USER");
    }

// ## Mesma coisa mas sem usar classes ou records ##
//    @PreAuthorize("@securityUtil.hasUserRole()")
//    @PostMapping(value = "/save-image")
//    public ResponseEntity<String> saveImage(
//            @RequestParam("id") @NotBlank String id,
//            @RequestParam("name") @NotBlank String name,
//            @RequestParam("date") @NotBlank String date,
//            @RequestParam("image") MultipartFile image
//    ) {
//        System.out.println("ID: " + id);
//        System.out.println("Nome: " + name);
//        System.out.println("DataTime: " + date);
//        System.out.println("Foto: " + image.getOriginalFilename());
//
//        return ResponseEntity.ok("Dados e imagem recebidos com sucesso.");
//    }

    @PreAuthorize("@securityUtil.hasUserRole()")
    @PostMapping(value = "/save-image")
    public ResponseEntity<String> saveImage(
            @ModelAttribute PayloadWithImageDto request,
            @RequestParam("image") MultipartFile image
    ) {
        System.out.println("ID: " + request.id());
        System.out.println("Nome: " + request.name());
        System.out.println("DataTime: " + request.date());
        System.out.println("Foto: " + image.getOriginalFilename());

        return ResponseEntity.ok("Dados e imagem recebidos com sucesso.");
    }
}
