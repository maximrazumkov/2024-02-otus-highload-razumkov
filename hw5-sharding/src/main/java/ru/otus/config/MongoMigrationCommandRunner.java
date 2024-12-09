package ru.otus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoMigrationCommandRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public String run(String... args) throws Exception {
        enableSharding();
        shardCollections();
        return "enableSharding";
    }

    private void enableSharding() {
        // Пример команды включения шардирования для коллекции
        mongoTemplate.executeCommand("{ enableSharding: 'chatApp' }");
    }

    private void shardCollections() {
        // Пример создания индексов для коллекций
        mongoTemplate.getCollection("dialogs").createIndex(new org.bson.Document("_id", "hashed"));
        mongoTemplate.executeCommand("{ shardCollection: 'chatApp.dialogs', key: { _id: 'hashed' } }");

        mongoTemplate.getCollection("messages").createIndex(new org.bson.Document("dialogId", "hashed"));
        mongoTemplate.executeCommand("{ shardCollection: 'chatApp.messages', key: { dialogId: 'hashed' } }");
    }
}
