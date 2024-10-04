package com.exemplo.estudos_java.infra.health;

import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomHealthCheck implements HealthIndicator {

    private final MongoTemplate mongoTemplate;
    private final RedisTemplate<String, String> redisTemplate;

    public CustomHealthCheck(MongoTemplate mongoTemplate, RedisTemplate<String, String> redisTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Health health() {
        Health.Builder healthBuilder = Health.up();

        // Verifica o MongoDB
        try {
            MongoDatabase db = mongoTemplate.getDb();
            db.runCommand(new Document("ping", 1)); // Executa o comando ping
        } catch (Exception e) {
            healthBuilder.down().withDetail("MongoDB", "Not reachable");
        }

        // Verifica o Redis
        try {
            Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection().ping();
        } catch (Exception e) {
            healthBuilder.down().withDetail("Redis", "Not reachable");
        }

        return healthBuilder.build();
    }

//    @Override
//    public Health getHealth(boolean includeDetails) {
//        Health health = this.health();
//        return Health.down().withDetails(health.getDetails()).build();
//    }

//    @Override
//    public Health health() {
//        boolean mongoIsUp = checkMongoDb(); // Implement your check here
//        boolean redisIsUp = checkRedis(); // Implement your check here
//
//        if (mongoIsUp && redisIsUp) {
//            return Health.up()
//                    .withDetail("mongo", "Available")
//                    .withDetail("redis", "Available")
//                    .build();
//        } else {
//            Health.Builder healthBuilder = Health.down();
//            if (!mongoIsUp) {
//                healthBuilder.withDetail("mongo", "Not available");
//            }
//            if (!redisIsUp) {
//                healthBuilder.withDetail("redis", "Not available");
//            }
//            return healthBuilder.build();
//        }
//    }
//
//    private boolean checkMongoDb() {
//        // Implement your MongoDB check logic
//        return false; // Change this based on actual check
//    }
//
//    private boolean checkRedis() {
//        // Implement your Redis check logic
//        return false; // Change this based on actual check
//    }
}