package ru.otus.highload.domain;

import java.sql.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private UUID id;
    private String firstName;
    private String secondName;
    private Date birthdate;
    private String gender;
    private String biography;
    private String city;
    private Boolean isCelebrity;
    private String password;

    @Override
    public String toString() {
        return "User{" +
            "firstName='" + firstName + '\'' +
            ", secondName='" + secondName + '\'' +
            ", birthdate=" + birthdate +
            ", gender='" + gender + '\'' +
            ", biography='" + biography + '\'' +
            ", city='" + city + '\'' +
            '}';
    }
}
