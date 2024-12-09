package ru.otus.repository;

import ru.otus.domain.Dialog;

import java.util.UUID;

public interface DialogRepository {
    void createDialog(Dialog dialog);
    void deleteDialog(UUID dialogId);
    Dialog getDialog(UUID dialogId);
}
