package ru.otus.highload.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Friend {
    private UUID id;
    @JsonProperty("user_id")
    private UUID usrId;
    @JsonProperty("friend_id")
    private UUID friendId;
}
