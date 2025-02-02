package ru.otus.highload.repository.jdbc;

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
public class UserRepositoryJdbc implements UserRepository {

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
    public UUID saveUser(User user) {
        try {
            final KeyHolder keyHolder = new GeneratedKeyHolder();
            final String query = "INSERT INTO public.usr(\n"
                + "\tfirst_name, second_name, birthdate, gender, biography, city, is_celebrity, password)\n"
                + "\tVALUES (?, ?, ?, ?, ?, ?, ?);";
            jdbcTemplate.update(connect -> {
                PreparedStatement ps = connect.prepareStatement(query, new String[]{"id"});
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getSecondName());
                ps.setDate(3, user.getBirthdate());
                ps.setString(4, user.getGender());
                ps.setString(5, user.getBiography());
                ps.setString(6, user.getCity());
                ps.setBoolean(7, user.getIsCelebrity());
                ps.setString(8, user.getPassword());
                return ps;
            }, keyHolder);
            return (UUID) Objects.requireNonNull(keyHolder.getKeys()).get("id");
        } catch (Exception e) {
            throw handleError(e, String.format("Не удалось создать пользователя %s", user));
        }
    }

    @Override
    public List<User> findUserByFirstNameAndLastName(String firstName, String lastName) {
        try {
            final String query = "select * from usr where first_name like ? and second_name like ?;";
            return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class), firstName + "%", lastName + "%");
        } catch (Exception e) {
            final String error = String.format(
                    "Ошибка при попытке найти пользователя по first_name = %s и second_name = %s",
                    firstName, lastName
                );
            throw handleError(e, error);
        }
    }

    @Override
    public void updateIsCelebrityById(UUID usrId, boolean isCelebrity) {
        String sql = "UPDATE usr SET is_celebrity = ? WHERE id = ?";
        jdbcTemplate.update(sql, isCelebrity, usrId);
    }


    private InternalException handleError(Exception e, String error) {
        log.error(error, e);
        return new InternalException(error, e);
    }
}
