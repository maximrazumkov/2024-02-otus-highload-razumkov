package ru.otus.highload.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDtoWithPassword extends UserDto {
    private String password;

    public UserDtoWithPassword(String firstName, String secondName, String birthdate,
        String gender, String biography, String city, String password) {
        super(firstName, secondName, birthdate, gender, biography, city);
        this.password = password;
    }

    public UserDtoWithPassword() {
    }
}
