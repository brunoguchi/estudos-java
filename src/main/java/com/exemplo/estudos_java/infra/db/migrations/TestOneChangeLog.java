package com.exemplo.estudos_java.infra.db.migrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@ChangeUnit(id = "addDescriptionFieldToMongoTests", order = "1", author = "Noguchi")
public class TestOneChangeLog {
    private final String collectionName = "mongotests";

    @Execution
    public void execute(MongoTemplate mongoTemplate) {

        mongoTemplate.updateMulti(
                new Query(), // Aplica a atualização a todos os documentos
                new Update().set("description", "ABCDE"),
                collectionName
        );

        // Atualiza o campo "status" somente se não existir
        mongoTemplate.updateMulti(
                query(where("status").exists(false)),
                new Update().set("status", "ativo"),
                collectionName
        );
    }

    @RollbackExecution
    public void rollback(MongoTemplate mongoTemplate) {
        mongoTemplate.updateMulti(
                query(where("description").exists(true)),
                new Update().unset("description"),
                collectionName
        );

        mongoTemplate.updateMulti(
                query(where("status").exists(true)),
                new Update().unset("status"),
                collectionName
        );
    }
}
