package ru.otus.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class MongoDBMigrationConfig {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Bean
    public ApplicationRunner mongoMigrationRunner() {
        return args -> {
            try (MongoClient mongoClient = MongoClients.create(mongoUri)) {
                Set<String> dbNames = mongoClient.listDatabaseNames().into(new java.util.HashSet<>());
                String dbName = mongoUri.substring(mongoUri.lastIndexOf("/") + 1);
                if (!dbNames.contains(dbName)) {
                    // Enable sharding for the database
                    MongoDatabase adminDb = mongoClient.getDatabase("admin");
                    adminDb.runCommand(new Document("enableSharding", "chatApp"));

                    // Shard "dialogs" collection
                    MongoDatabase chatAppDb = mongoClient.getDatabase("chatApp");
                    chatAppDb.getCollection("dialogs")
                            .createIndex(new Document("_id", "hashed"));
                    adminDb.runCommand(new Document("shardCollection", "chatApp.dialogs")
                            .append("key", new Document("_id", "hashed")));

                    // Shard "messages" collection
                    chatAppDb.getCollection("messages")
                            .createIndex(new Document("dialogId", "hashed"));
                    adminDb.runCommand(new Document("shardCollection", "chatApp.messages")
                            .append("key", new Document("dialogId", "hashed")));


                    //

                    //

                    System.out.println("MongoDB migrations completed successfully.");
                } else {
                    System.out.println("Database " + dbName + " already exists. Skipping creation.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("MongoDB migrations failed", e);
            }
        };
    }
}
