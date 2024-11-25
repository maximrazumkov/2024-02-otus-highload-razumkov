package ru.otus.highload.repository;

import java.util.List;
import java.util.UUID;
import ru.otus.highload.domain.User;

public interface UserRepository {
    User getUserById(UUID id);
    UUID saveUser(User user);
    List<User> findUserByFirstNameAndLastName(String firstName, String lastName);
    void updateIsCelebrityById(UUID usrId, boolean isCelebrity);
}
