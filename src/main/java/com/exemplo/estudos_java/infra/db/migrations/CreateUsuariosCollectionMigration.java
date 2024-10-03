package com.exemplo.estudos_java.infra.db.migrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "createUsuariosCollection", order = "2", author = "Noguchi")
public class CreateUsuariosCollectionMigration {
    private final String collectionName = "usuario";

    @Execution
    public void execute(MongoTemplate mongoTemplate) {
        if (!mongoTemplate.collectionExists(collectionName)) {
            mongoTemplate.createCollection(collectionName);
        }

        mongoTemplate.insert(new Document("_id", new org.bson.types.ObjectId())
                .append("name", "Alice Smith"), collectionName);

        mongoTemplate.insert(new Document("_id", new org.bson.types.ObjectId())
                .append("name", "Bob Johnson"), collectionName);

        mongoTemplate.insert(new Document("_id", new org.bson.types.ObjectId())
                .append("name", "Charlie Brown"), collectionName);
    }

    @RollbackExecution
    public void rollback(MongoTemplate mongoTemplate) {
        mongoTemplate.dropCollection(collectionName);
    }
}