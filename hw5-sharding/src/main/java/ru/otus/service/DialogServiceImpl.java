package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Dialog;
import ru.otus.domain.Message;
import ru.otus.repository.DialogRepository;
import ru.otus.repository.MessageRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DialogServiceImpl implements DialogService {

    private final DialogRepository dialogRepository;
    private final MessageRepository messageRepository;


    @Override
    public void createDialog(Dialog dialog) {
        dialog.setCreatedAt(Instant.now());
        dialog.setId(UUID.randomUUID());
        dialogRepository.createDialog(dialog);
    }



    @Override
    @Transactional
    public void deleteDialog(UUID dialogId) {
        dialogRepository.deleteDialog(dialogId);
        messageRepository.deleteMessagesByDialogId(dialogId);
    }

    @Override
    public List<Message> getMessages(UUID dialogId) {
        return messageRepository.findMessagesByDialogId(dialogId);
    }

    @Override
    public void createMessage(UUID dialogId, Message message) {
        message.setCreatedAt(Instant.now());
        message.setId(UUID.randomUUID());
        messageRepository.createMessage(dialogId, message);
    }

    @Override
    public void editMessage(UUID dialogId, UUID messageId, Message message) {
        message.setUpdatedAt(Instant.now());
        messageRepository.updateMessage(dialogId, messageId, message);
    }

    @Override
    public Dialog getDialog(UUID dialogId) {
        return dialogRepository.getDialog(dialogId);
    }
}
