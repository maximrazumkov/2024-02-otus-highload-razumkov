package ru.otus.highload.sevice.impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.otus.highload.domain.Post;
import ru.otus.highload.repository.FriendRepository;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostWebSocketHandler extends TextWebSocketHandler {

    private final FriendRepository friendRepository;
    private final ObjectMapper objectMapper;
    private final Map<WebSocketSession, UUID> sessionUserMap = new ConcurrentHashMap<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        UUID userId = extractUserIdFromSession(session);
        if (userId != null) {
            sessionUserMap.put(session, userId);
        } else {
            session.close(CloseStatus.BAD_DATA);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        UUID userId = sessionUserMap.get(session);
        if (userId == null) {
            session.close(CloseStatus.BAD_DATA);
            return;
        }

        List<UUID> subscribedUserIds = friendRepository.findSubscribeIdListByUsrId(userId);

        for (Map.Entry<WebSocketSession, UUID> entry : sessionUserMap.entrySet()) {
            UUID senderId = entry.getValue();
            if (subscribedUserIds.contains(senderId)) {
                entry.getKey().sendMessage(message);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessionUserMap.remove(session);
    }

    private UUID extractUserIdFromSession(WebSocketSession session) {
        String uri = session.getUri().toString();
        String userIdParam = uri.split("\\?userId=")[1];
        return UUID.fromString(userIdParam);
    }

    public void broadcast(Post post) {
        try {
            String postJson = objectMapper.writeValueAsString(post);
            List<UUID> subscribedUserIds = friendRepository.findSubscribeIdListByUsrId(post.getUsrId());
            for (Map.Entry<WebSocketSession, UUID> entry : sessionUserMap.entrySet()) {
                UUID senderId = entry.getValue();
                if (subscribedUserIds.contains(senderId)) {
                    entry.getKey().sendMessage(new TextMessage(postJson));
                }
            }
        } catch (Exception e) {
            log.error("Failed to broadcast message", e);
        }
    }
}
