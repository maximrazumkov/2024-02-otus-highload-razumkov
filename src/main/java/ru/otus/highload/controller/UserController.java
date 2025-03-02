package ru.otus.highload.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.highload.dto.LoginDto;
import ru.otus.highload.dto.UserDto;
import ru.otus.highload.dto.UserDtoWithPassword;
import ru.otus.highload.sevice.UserService;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/get/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/user/search")
    public ResponseEntity<List<UserDto>> searchUser(
        @RequestParam("first_name") String firstName,
        @RequestParam("last_name") String lastName
    ) {
        return ResponseEntity.ok(userService.searchUser(firstName, lastName));
    }

    @PostMapping("/user/register")
    public ResponseEntity<Map<String, UUID>> createUser(@RequestBody UserDtoWithPassword userDto) {
        UUID id = userService.createUser(userDto);
        return ResponseEntity.ok(Map.of("user_id", id));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDto loginDto) {
        String token = userService.login(loginDto);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
