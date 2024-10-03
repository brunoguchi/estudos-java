package com.exemplo.estudos_java.infra.db.migrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "createUsuariosCollection", order = "2", author = "Noguchi")
public class CreateUsuariosCollectionMigration {

    @Execution
    public void execute(MongoTemplate mongoTemplate) {
        // Verifica se a coleção "usuarios" existe
        if (!mongoTemplate.collectionExists("usuarios")) {
            // Cria a coleção "usuarios"
            mongoTemplate.createCollection("usuarios");

            // Insere 3 novos usuários
            Document user1 = new Document("_id", new org.bson.types.ObjectId())
                    .append("Name", "Alice Smith");
            Document user2 = new Document("_id", new org.bson.types.ObjectId())
                    .append("Name", "Bob Johnson");
            Document user3 = new Document("_id", new org.bson.types.ObjectId())
                    .append("Name", "Charlie Brown");

            // Adiciona os usuários à coleção
            mongoTemplate.insert(user1, "usuarios");
            mongoTemplate.insert(user2, "usuarios");
            mongoTemplate.insert(user3, "usuarios");
        }
    }

    @RollbackExecution
    public void rollback(MongoTemplate mongoTemplate) {
        // Remove a coleção "usuarios" caso seja necessário reverter
        mongoTemplate.dropCollection("usuarios");
    }
}