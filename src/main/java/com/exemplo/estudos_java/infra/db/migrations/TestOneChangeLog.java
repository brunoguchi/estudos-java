package com.exemplo.estudos_java.infra.db.migrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@ChangeUnit(id = "addDescriptionFieldToMongoTests", order = "1", author = "Noguchi")
public class TestOneChangeLog {

    @Execution
    public void execute(MongoTemplate mongoTemplate) {
        // Aqui você pode realizar qualquer alteração, como adicionar um campo em uma coleção
        mongoTemplate.updateMulti(
                query(where("description").exists(false)),
                new Update().set("description", "ABCDE"), "mongotests"
        );
    }

    @RollbackExecution
    public void rollback(MongoTemplate mongoTemplate) {
        // Código de rollback caso seja necessário reverter
        mongoTemplate.updateMulti(
                query(where("description").exists(true)),
                new Update().unset("description"), "mongotests"
        );
    }
}
