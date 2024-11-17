package ru.otus.highload.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.highload.domain.Post;
import ru.otus.highload.exception.NotFoundException;
import ru.otus.highload.repository.PostRepository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class PostRepositoryJdbc implements PostRepository {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public UUID createPost(UUID usrId, String text) {
        String sql = "INSERT INTO post (usr_id, post, created_at) VALUES (?, ?, CURRENT_TIMESTAMP) RETURNING id";
        return jdbcTemplate.queryForObject(sql, UUID.class, usrId, text);
    }

    @Override
    public void updatePost(UUID postId, String text) {
        String sql = "UPDATE post SET post = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(sql, text, postId);
    }

    @Override
    public Post getPost(UUID postId) {
        try {
            String sql = "SELECT * FROM post WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                Post post = new Post();
                post.setId(UUID.fromString(rs.getString("id")));
                post.setUsrId(UUID.fromString(rs.getString("usr_id")));
                post.setText(rs.getString("post"));
                post.setCreatedAt(rs.getTimestamp("created_at"));
                post.setUpdatedAt(rs.getTimestamp("updated_at"));
                return post;
            }, postId);
        } catch (Exception e) {
            throw new NotFoundException("Пост не найден");
        }
    }

    @Override
    public void deletePost(UUID id) {
        String sql = "DELETE FROM post WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Post> feedPosts(UUID usrId, int offset, int limit) {
        String sql = "SELECT p.* FROM post p JOIN friend f ON f.friend_id = p.usr_id WHERE f.usr_id = ? ORDER BY created_at Desc LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Post post = new Post();
            post.setId(UUID.fromString(rs.getString("id")));
            post.setUsrId(UUID.fromString(rs.getString("usr_id")));
            post.setText(rs.getString("post"));
            post.setCreatedAt(rs.getTimestamp("created_at"));
            post.setUpdatedAt(rs.getTimestamp("updated_at"));
            return post;
        }, usrId, limit, offset);
    }
}
