package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Dialog;
import ru.otus.domain.Message;
import ru.otus.dto.DialogDto;
import ru.otus.dto.MessageDto;
import ru.otus.service.DialogService;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dialogs")
@RequiredArgsConstructor
public class DialogController {

    private final DialogService dialogService;

    @PostMapping
    public ResponseEntity<DialogDto> createDialog(@RequestBody DialogDto dialogDto) {
        Dialog dialog = new Dialog();
        dialog.setTitle(dialogDto.getTitle());
        dialog.setParticipants(dialogDto.getParticipants());
        dialog.setCreatedAt(Instant.now());
        dialog.setUpdatedAt(Instant.now());
        dialogService.createDialog(dialog);
        dialogDto.setId(dialog.getId());
        return ResponseEntity.ok(dialogDto);
    }

    @GetMapping("/{dialogId}")
    public ResponseEntity<DialogDto> getDialog(@PathVariable UUID dialogId) {
        Dialog dialog = dialogService.getDialog(dialogId);
        DialogDto dialogDto = new DialogDto();
        dialogDto.setId(dialog.getId());
        dialogDto.setTitle(dialog.getTitle());
        dialogDto.setParticipants(dialog.getParticipants());
        return ResponseEntity.ok(dialogDto);
    }

    @DeleteMapping("/{dialogId}")
    public ResponseEntity<String> deleteDialog(@PathVariable UUID dialogId) {
        dialogService.deleteDialog(dialogId);
        return ResponseEntity.ok("Dialog deleted successfully");
    }

    @GetMapping("/{dialogId}/messages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable UUID dialogId) {
        return ResponseEntity.ok(dialogService.getMessages(dialogId));
    }

    @PostMapping("/{dialogId}/messages")
    public ResponseEntity<String> createMessage(
            @PathVariable UUID dialogId,
            @RequestBody MessageDto messageDto
    ) {
        Message message = new Message();
        message.setDialogId(dialogId);
        message.setSenderId(messageDto.getSenderId());
        message.setSenderName(messageDto.getSenderName());
        message.setMessageText(messageDto.getMessageText());
        message.setCreatedAt(Instant.now());
        message.setUpdatedAt(Instant.now());
        dialogService.createMessage(dialogId, message);
        return ResponseEntity.ok("Message created successfully");
    }

    @PutMapping("/{dialogId}/messages/{messageId}")
    public ResponseEntity<String> editMessage(
            @PathVariable UUID dialogId,
            @PathVariable UUID messageId,
            @RequestBody MessageDto messageDto
    ) {
        Message message = new Message();
        message.setMessageText(messageDto.getMessageText());
        message.setUpdatedAt(Instant.now());
        dialogService.editMessage(dialogId, messageId, message);
        return ResponseEntity.ok("Message updated successfully");
    }
}
