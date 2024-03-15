package ru.otus.highload.repository.impl;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.highload.domain.User;
import ru.otus.highload.exception.InternalException;
import ru.otus.highload.exception.NotFoundException;
import ru.otus.highload.repository.UserRepository;

@Slf4j
@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public User getUserById(UUID id) {
        try {
            final String query = "select * from usr where id = ?;";
            return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(User.class), id);
        } catch (EmptyResultDataAccessException er) {
            log.error("Пользователь не найден.");
            throw new NotFoundException("Пользователь не найден.");
        } catch (Exception e) {
            throw handleError(e, String.format("Ошибка при попытке найти пользователя по id = %s. ", id));
        }
    }

    @Override
    public UUID createUser(User user) {
        try {
            final KeyHolder keyHolder = new GeneratedKeyHolder();
            final String query = "INSERT INTO public.usr(\n"
                + "\tfirst_name, second_name, birthdate, gender, biography, city, password)\n"
                + "\tVALUES (?, ?, ?, ?, ?, ?, ?);";
            jdbcTemplate.update(connect -> {
                PreparedStatement ps = connect.prepareStatement(query, new String[]{"id"});
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getSecondName());
                ps.setDate(3, user.getBirthdate());
                ps.setString(4, user.getGender());
                ps.setString(5, user.getBiography());
                ps.setString(6, user.getCity());
                ps.setString(7, user.getPassword());
                return ps;
            }, keyHolder);
            return (UUID) Objects.requireNonNull(keyHolder.getKeys()).get("id");
        } catch (Exception e) {
            throw handleError(e, String.format("Не удалось создать пользователя %s", user));
        }
    }

    @Override
    public List<User> search(String firstName, String lastName) {
        try {
            final String query = "select * from usr where first_name = ? and last_name = ?;";
            return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class), firstName, lastName);
        } catch (Exception e) {
            final String error = String.format(
                    "Ошибка при попытке найти пользователя по first_name = %s и last_name = %s",
                    firstName, lastName
                );
            throw handleError(e, error);
        }
    }

    private InternalException handleError(Exception e, String error) {
        log.error(error, e);
        return new InternalException(error, e);
    }
}
