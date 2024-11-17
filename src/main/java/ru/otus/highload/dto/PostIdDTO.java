package ru.otus.highload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostIdDTO {
    @JsonProperty("post_id")
    private UUID postId;
}
