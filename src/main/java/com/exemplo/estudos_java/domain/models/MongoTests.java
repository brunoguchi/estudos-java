package com.exemplo.estudos_java.domain.models;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "mongotests")
public class MongoTests {
    @Id
    private ObjectId id;
    private String identify;
    private String nome;
}
