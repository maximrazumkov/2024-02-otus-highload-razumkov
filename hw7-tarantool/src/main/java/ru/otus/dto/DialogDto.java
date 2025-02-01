package ru.otus.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class DialogDto {

    private UUID dialogId;

    private UUID from;

    private String text;

    private Instant createAt;

}
