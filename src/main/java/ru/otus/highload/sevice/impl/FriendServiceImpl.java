package ru.otus.highload.sevice.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.highload.repository.FriendRepository;
import ru.otus.highload.sevice.FriendService;
import ru.otus.highload.sevice.UserService;

import java.util.UUID;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    @Value("${celebrity-count:1000}")
    private Long celebrityCount;

    private final FriendRepository friendRepository;
    private final UserService userService;

    @Override
    @Transactional
    public void addFriend(UUID usrId, UUID friendId) {
        friendRepository.addFriend(usrId, friendId);
        updateUserCelebrity(friendId, (countFriendsByUserId) -> countFriendsByUserId >= celebrityCount, true);
    }

    @Override
    @Transactional
    public void deleteFriend(UUID usrId, UUID friendId) {
        friendRepository.deleteFriend(usrId, friendId);
        updateUserCelebrity(usrId, (countFriendsByUserId) -> countFriendsByUserId < celebrityCount, false);
    }

    private void updateUserCelebrity(UUID friendId, Predicate<Long> predicate, boolean isCelebrity) {
        Long countFriendsByUserId = friendRepository.getCountSubscribeByUserId(friendId);
        if (predicate.test(countFriendsByUserId)) {
            userService.updateIsCelebrityById(friendId.toString(), isCelebrity);
        }
    }
}
