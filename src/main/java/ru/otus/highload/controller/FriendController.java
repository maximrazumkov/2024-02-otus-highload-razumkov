package ru.otus.highload.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.highload.dto.FriendDto;
import ru.otus.highload.sevice.FriendService;

import java.util.UUID;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @PostMapping("/add/{user_id}")
    public ResponseEntity<Void> addFriend(@PathVariable("user_id") UUID userId, @RequestBody FriendDto friendDto) {
        friendService.addFriend(userId, friendDto.getFriendId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable("user_id") UUID userId, @RequestBody FriendDto friendDto) {
        friendService.deleteFriend(userId, friendDto.getFriendId());
        return ResponseEntity.ok().build();
    }
}
