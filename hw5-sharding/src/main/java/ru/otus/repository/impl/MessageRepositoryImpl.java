package ru.otus.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Message;
import ru.otus.repository.MessageRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepository {

    private final MongoTemplate mongoTemplate;

    public List<Message> findMessagesByDialogId(UUID dialogId) {
        Query query = new Query(Criteria.where("dialogId").is(dialogId));
        return mongoTemplate.find(query, Message.class, "messages");
    }

    public void createMessage(UUID dialogId, Message message) {
        mongoTemplate.insert(message, "messages");
    }

    public void updateMessage(UUID dialogId, UUID messageId, Message message) {
        Query query = new Query(
                Criteria.where("_id").is(messageId).and("dialogId").is(dialogId)
        );
        Update update = new Update()
                .set("messageText", message.getMessageText())
                .set("updatedAt", new Date());
        mongoTemplate.updateFirst(query, update, "messages");
    }

    public void deleteMessagesByDialogId(UUID dialogId) {
        Query query = new Query(Criteria.where("dialogId").is(dialogId));
        mongoTemplate.remove(query, "messages");
    }
}
