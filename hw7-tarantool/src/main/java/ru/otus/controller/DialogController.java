package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.otus.dto.DialogDto;
import ru.otus.dto.NewDialogDto;
import ru.otus.service.DialogService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("dialog")
@RequiredArgsConstructor
public class DialogController {

    @Autowired
    private final DialogService dialogService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{dialogId}")
    public void addMessage(@RequestHeader("X-Auth-User") UUID userId, @PathVariable UUID dialogId, @RequestBody NewDialogDto newDialogDto) {
        dialogService.addMessage(dialogId, userId, newDialogDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{dialogId}")
    public List<DialogDto> getDialogs(@RequestHeader("X-Auth-User") UUID userId, @PathVariable UUID dialogId) {
        return dialogService.getDialog(dialogId, userId);
    }
}
