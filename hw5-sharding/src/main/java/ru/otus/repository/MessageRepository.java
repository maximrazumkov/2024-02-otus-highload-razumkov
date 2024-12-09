package ru.otus.repository;


import ru.otus.domain.Message;

import java.util.List;
import java.util.UUID;

public interface MessageRepository {
    List<Message> findMessagesByDialogId(UUID dialogId);
    void createMessage(UUID dialogId, Message message);
    void updateMessage(UUID dialogId, UUID messageId, Message message);
    void deleteMessagesByDialogId(UUID dialogId);
}
