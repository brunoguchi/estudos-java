package com.exemplo.estudos_java.infra.repository;

import com.exemplo.estudos_java.domain.models.MongoTests;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyModelRepository extends MongoRepository<MongoTests, String> {
}
