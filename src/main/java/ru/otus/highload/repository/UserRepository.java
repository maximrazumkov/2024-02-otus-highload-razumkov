package ru.otus.highload.repository;

import java.util.List;
import java.util.UUID;
import ru.otus.highload.domain.User;

public interface UserRepository {
    User getUserById(UUID id);
    UUID createUser(User user);
    List<User> search(String firstName, String lastName);
}
