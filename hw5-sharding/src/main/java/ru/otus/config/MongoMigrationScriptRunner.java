package ru.otus.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

//@Configuration
public class MongoMigrationScriptRunner {

    //@Bean
    public ApplicationRunner runMongoScript() {
        return args -> {
            String scriptPath = "scripts/init-mongo.js"; // Укажите путь к JS-файлу
            String mongoUri = "mongodb://localhost:27027/chatApp";
            ProcessBuilder pb = new ProcessBuilder("mongo", "--host", mongoUri, scriptPath);
            try {
                Process process = pb.start();
                String output = new BufferedReader(new InputStreamReader(process.getInputStream()))
                        .lines()
                        .collect(Collectors.joining("\n"));
                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    System.out.println("MongoDB script executed successfully:\n" + output);
                } else {
                    System.err.println("Error executing MongoDB script:\n" + output);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("MongoDB migration script failed", e);
            }
        };
    }
}
