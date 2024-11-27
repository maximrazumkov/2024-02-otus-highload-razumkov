package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    private UUID id; // MongoDB ObjectId
    private UUID dialogId; // ID of the dialog this message belongs to
    private String senderId; // Sender ID
    private String senderName; // Sender name
    private String messageText; // Message content
    private Instant createdAt; // Creation timestamp
    private Instant updatedAt; // Last update timestamp
}