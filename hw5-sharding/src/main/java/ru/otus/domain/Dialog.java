package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Document(collection = "dialogs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dialog {
    @Id
    private UUID id; // MongoDB ObjectId
    private String title; // Optional dialog name
    private List<Participant> participants; // List of participants
    private Instant createdAt; // Creation timestamp
    private Instant updatedAt; // Last update timestamp

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Participant {
        private UUID userId;    // User ID
        private String userName;  // User name
    }
}


