package ru.otus.highload.sevice;

import java.util.UUID;

public interface FriendService {
    void addFriend(UUID usrId, UUID friendId);
    void deleteFriend(UUID usrId, UUID friendId);
}
