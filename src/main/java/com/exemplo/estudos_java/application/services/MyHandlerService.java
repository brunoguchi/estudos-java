package com.exemplo.estudos_java.application.services;

import com.exemplo.estudos_java.application.dtos.TestForMongoDto;
import com.exemplo.estudos_java.domain.models.MongoTests;
import com.exemplo.estudos_java.infra.repository.MyModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyHandlerService {

    @Autowired
    private MyModelRepository modelRepository;

    public void handle(TestForMongoDto myDTO) {
        var model = new MongoTests();
        model.setIdentify(myDTO.getIdentify());
        model.setNome(myDTO.getName());
        modelRepository.save(model);
    }

    public List<MongoTests> getAllModels() {
        return modelRepository.findAll();
    }
}