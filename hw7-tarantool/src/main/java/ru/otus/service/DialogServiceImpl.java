package ru.otus.service;

import io.tarantool.driver.api.TarantoolClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dto.DialogDto;
import ru.otus.dto.NewDialogDto;
import ru.otus.exception.ModelNotFoundException;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DialogServiceImpl implements DialogService {

    private final TarantoolClient client;

    @Override
    public void addMessage(UUID dialogId, UUID userId, NewDialogDto newDialogDto) {
        try {
            client.call("addMessage", dialogId, userId, newDialogDto.getText(), Instant.now());
        } catch (Exception e) {
            throw new RuntimeException("bad request");
        }
    }

    @Override
    public List<DialogDto> getDialog(UUID dialogId, UUID userId) {
        try {
            List<Object> result = fetchDialogs(dialogId);
            return mapToDialogDtoList(result);
        } catch (Exception e) {
            throw new ModelNotFoundException("Dialogs not found", e);
        }
    }

    private List<Object> fetchDialogs(UUID dialogId) throws Exception {
        return (List<Object>) client.call("getDialog", dialogId).get().get(0);
    }

    private List<DialogDto> mapToDialogDtoList(List<Object> result) {
        return result.stream()
                .map(this::mapToDialogDto)
                .toList();
    }

    private DialogDto mapToDialogDto(Object rawData) {
        List<Object> data = castToList(rawData);
        DialogDto dialogDto = new DialogDto();
        dialogDto.setDialogId(UUID.fromString(data.get(1).toString()));
        dialogDto.setFrom(UUID.fromString(data.get(2).toString()));
        dialogDto.setText(data.get(3).toString());
        dialogDto.setCreateAt(Instant.parse(data.get(4).toString()));
        return dialogDto;
    }

    @SuppressWarnings("unchecked")
    private List<Object> castToList(Object obj) {
        if (obj instanceof List<?>) {
            return (List<Object>) obj;
        }
        throw new IllegalArgumentException("Invalid data format");
    }
}
