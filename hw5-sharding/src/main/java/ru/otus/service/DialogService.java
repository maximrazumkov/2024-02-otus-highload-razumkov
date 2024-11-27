package ru.otus.service;

import ru.otus.domain.Dialog;
import ru.otus.domain.Message;

import java.util.List;
import java.util.UUID;

public interface DialogService {
    void createDialog(Dialog request);
    void deleteDialog(UUID dialogId);
    Dialog getDialog(UUID dialogId);
    List<Message> getMessages(UUID dialogId);
    void createMessage(UUID dialogId, Message request);
    void editMessage(UUID dialogId, UUID messageId, Message request);
}
