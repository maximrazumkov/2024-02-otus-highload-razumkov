package ru.otus.highload.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    @JsonProperty("user_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID userId;

    @JsonProperty("post_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID postId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String post;

    @JsonProperty("created_at")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm Z")
    private Timestamp createdAt;

    @JsonProperty("updated_at")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm Z")
    private Timestamp updatedAt;
}
