package ru.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.domain.Dialog;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DialogDto {
    private UUID id; // MongoDB ObjectId
    private String title;
    private List<Dialog.Participant> participants;
}
