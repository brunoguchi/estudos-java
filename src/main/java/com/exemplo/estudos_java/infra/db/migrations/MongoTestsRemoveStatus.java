package com.exemplo.estudos_java.infra.db.migrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@ChangeUnit(id = "removeStatusFieldFromMongoTests", order = "3", author = "Noguchi")
public class MongoTestsRemoveStatus {
    private final String collectionName = "mongotests";

    @Execution
    public void execute(MongoTemplate mongoTemplate) {
        mongoTemplate.updateMulti(
                query(where("status").exists(true)),
                new Update().unset("status"),
                collectionName
        );
    }

    @RollbackExecution
    public void rollback(MongoTemplate mongoTemplate) {
    }
}