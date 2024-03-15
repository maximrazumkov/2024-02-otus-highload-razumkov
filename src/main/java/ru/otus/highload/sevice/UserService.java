package ru.otus.highload.sevice;

import java.util.List;
import java.util.UUID;
import ru.otus.highload.dto.LoginDto;
import ru.otus.highload.dto.UserDto;
import ru.otus.highload.dto.UserDtoWithPassword;

public interface UserService {
    UserDto getUser(String id);
    List<UserDto> searchUser(String firstName, String lastName);
    UUID createUser(UserDtoWithPassword userDto);
    String login(LoginDto loginDto);
}
