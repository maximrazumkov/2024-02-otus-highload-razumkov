package ru.otus.highload.sevice.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.highload.domain.User;
import ru.otus.highload.dto.LoginDto;
import ru.otus.highload.dto.UserDto;
import ru.otus.highload.dto.UserDtoWithPassword;
import ru.otus.highload.exception.BadRequestException;
import ru.otus.highload.exception.UnauthorizedException;
import ru.otus.highload.repository.UserRepository;
import ru.otus.highload.sevice.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Pbkdf2PasswordEncoder pbkdf2PasswordEncoder;

    @Override
    public UserDto getUser(String id) {
        User user = userRepository.getUserById(convertStringToUUID(id));
        return convertUserToUserDto(user);
    }

    @Override
    public List<UserDto> searchUser(String firstName, String lastName) {
        List<User> userList = userRepository.search(firstName, lastName);
        return getUserDtos(userList);
    }

    @Override
    public UUID createUser(UserDtoWithPassword userDto) {
        String password = pbkdf2PasswordEncoder.encode(userDto.getPassword());
        User user = convertUserDtoToUser(userDto);
        user.setPassword(password);
        return userRepository.createUser(user);
    }

    @Override
    public String login(LoginDto loginDto) {
        User user = userRepository.getUserById(convertStringToUUID(loginDto.getId()));
        if (!pbkdf2PasswordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Неверный пароль");
        }
        return getToken(user);
    }

    private String getToken(User user) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String fullName = user.getFirstName() + " " + user.getSecondName();
        return Jwts.builder()
            .setIssuer("Stormpath")
            .setSubject(fullName)
            .claim("name", fullName)
            .claim("scope", "users")
            .setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
            .setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    private UUID convertStringToUUID(String id) {
        try {
            return UUID.fromString(id);
        } catch (Exception e) {
            throw new BadRequestException("Id передан в неверном формате. ", e);
        }
    }

    private List<UserDto> getUserDtos(List<User> userList) {
        return userList.stream()
            .map(this::convertUserToUserDto)
            .toList();
    }

    private UserDto convertUserToUserDto(User user) {
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return UserDto.builder()
            .firstName(user.getFirstName())
            .secondName(user.getSecondName())
            .birthdate(df.format(user.getBirthdate()))
            .biography(user.getBiography())
            .gender(user.getGender())
            .city(user.getCity())
            .build();
    }

    private User convertUserDtoToUser(UserDto userDto) {
        return User.builder()
            .firstName(userDto.getFirstName())
            .secondName(userDto.getSecondName())
            .birthdate(convertStringToDate(userDto.getBirthdate()))
            .biography(userDto.getBiography())
            .gender(userDto.getGender())
            .city(userDto.getCity())
            .build();
    }

    private Date convertStringToDate(String dateString) {
        try {
            return Date.valueOf(dateString);
        } catch (Exception e) {
            throw new BadRequestException("Передан неверный формат даты", e);
        }
    }
}
