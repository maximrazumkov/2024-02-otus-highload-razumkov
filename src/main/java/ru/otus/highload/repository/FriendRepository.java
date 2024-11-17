package ru.otus.highload.repository;

import java.util.List;
import java.util.UUID;

public interface FriendRepository {
    void addFriend(UUID usrId, UUID friendId);
    void deleteFriend(UUID usrId, UUID friendId);
    Long getCountSubscribeByUserId(UUID friendId);
    List<UUID> findSubscribeIdListByUsrId(UUID usrId);
}
