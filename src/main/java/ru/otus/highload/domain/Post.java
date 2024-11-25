package ru.otus.highload.domain;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private UUID id;
    private UUID usrId;
    private String text;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
