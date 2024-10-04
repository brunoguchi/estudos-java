package com.exemplo.estudos_java.infra.repository;

import com.exemplo.estudos_java.domain.models.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<Users, ObjectId> {
    UserDetails findByLogin(String login);
}
