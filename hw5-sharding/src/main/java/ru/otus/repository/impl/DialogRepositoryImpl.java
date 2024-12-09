package ru.otus.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Dialog;
import ru.otus.repository.DialogRepository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DialogRepositoryImpl implements DialogRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public void createDialog(Dialog dialog) {
        mongoTemplate.insert(dialog, "dialogs");
    }

    @Override
    public void deleteDialog(UUID dialogId) {
        Query query = new Query(Criteria.where("_id").is(dialogId));
        mongoTemplate.remove(query, "dialogs");
    }

    @Override
    public Dialog getDialog(UUID dialogId) {
        return mongoTemplate.findById(dialogId, Dialog.class, "dialogs");
    }
}
