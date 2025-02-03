package ru.otus;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.UuidRepresentation;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.domain.Dialog;
import ru.otus.domain.Message;

import java.io.File;
import java.time.Instant;
import java.util.*;

public class Script {
    public static void main(String[] args) {
        try (
                Scanner scanner = new Scanner(new File("data-1728055239107.csv"));
                MongoClient mongoClient = MongoClients.create(MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString("mongodb://localhost:27027/chatApp"))
                        .uuidRepresentation(UuidRepresentation.JAVA_LEGACY)
                        .build())                        ;
        ) {
            scanner.useDelimiter("\\r?\\n");
            List<Dialog> dialogs = new ArrayList<>();
            MongoDatabase chatAppDb = mongoClient.getDatabase("chatApp");
            MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "chatApp");
            int x = 1;
            while (scanner.hasNext() && x < 200000) {
                String uuid = scanner.nextLine();
                Random r = new Random();
                int low = 2;
                int high = 5;
                int result = r.nextInt(high-low) + low;
                List<Dialog.Participant> participants = new ArrayList<>();
                for (int i = 0; i < result; ++i) {
                    Dialog.Participant participant = new Dialog.Participant();
                    participant.setUserId(UUID.randomUUID());
                    participant.setUserName("user" + i);
                    participants.add(participant);
                }
                Dialog dialog = new Dialog();
                dialog.setId(UUID.fromString(uuid));
                dialog.setParticipants(participants);
                dialog.setCreatedAt(Instant.now());
                dialogs.add(dialog);
                List<Message> messages = new ArrayList<>();
                for (int i = 0; i < 15; ++i) {

                    int leftLimit = 48; // numeral '0'
                    int rightLimit = 122; // letter 'z'
                    int targetStringLength = 10;
                    Random random = new Random();

                    String generatedString = random.ints(leftLimit, rightLimit + 1)
                            .filter(j -> (j <= 57 || j >= 65) && (j <= 90 || j >= 97))
                            .limit(targetStringLength)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString();

                    Message message = new Message();
                    message.setId(UUID.randomUUID());
                    message.setDialogId(UUID.fromString(uuid));
                    message.setSenderId(UUID.randomUUID().toString());
                    message.setSenderName("senderName" + i);
                    message.setMessageText("anytext " + generatedString + " " + x);
                    message.setCreatedAt(Instant.now());
                    messages.add(message);
                }
                mongoTemplate.insert(messages, "messages");
                ++x;
            }
            mongoTemplate.insert(dialogs, "dialogs");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
