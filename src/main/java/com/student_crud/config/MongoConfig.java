package com.student_crud.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/**
 * Enables @CreatedDate and @LastModifiedDate
 * auto-population on every MongoDB document save.
 */
@Configuration
@EnableMongoAuditing
public class MongoConfig {
}