package ru.otus.highload.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.highload.domain.Post;
import ru.otus.highload.domain.User;
import ru.otus.highload.repository.FriendRepository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class FriendRepositoryJdbc implements FriendRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void addFriend(UUID usrId, UUID friendId) {
        String sql = "INSERT INTO friend (usr_id, friend_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, usrId, friendId);
    }

    @Override
    public void deleteFriend(UUID usrId, UUID friendId) {
        String sql = "DELETE FROM friend WHERE usr_id = ? AND friend_id = ?";
        jdbcTemplate.update(sql, usrId, friendId);
    }

    @Override
    public Long getCountSubscribeByUserId(UUID friendId) {
        String sql = "SELECT count(usr_id) as count FROM friend WHERE friend_id = ? limit 1000;";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> rs.getLong("count"), friendId);
    }

    @Override
    public List<UUID> findSubscribeIdListByUsrId(UUID usrId) {
        String sql = "SELECT usr_id FROM friend WHERE friend_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> UUID.fromString(rs.getString("usr_id")), usrId);
    }

}
