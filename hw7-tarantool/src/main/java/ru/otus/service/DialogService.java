package ru.otus.service;

import ru.otus.dto.DialogDto;
import ru.otus.dto.NewDialogDto;

import java.util.List;
import java.util.UUID;

public interface DialogService {

    void addMessage(UUID dialogId, UUID userId, NewDialogDto dialogDto);

    List<DialogDto> getDialog(UUID dialogId, UUID userId);
}