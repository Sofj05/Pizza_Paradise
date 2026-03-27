package org.example.pizza_paradise.infrastructure;

import org.example.pizza_paradise.domain.IUserRepository;
import org.example.pizza_paradise.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository implements IUserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(User user) {
        String sql = """
                insert into users(name,email,address,points)
                values (?,?,?,?)
                """;
        jdbcTemplate.update(sql, ps -> {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getAddress());
            ps.setInt(4, user.getPoints());
        });
    }

    public User findByEmail(String email) {
        String sql = """
                SELECT name, email, address, points
                FROM Users
                WHERE email = ?;
                """;
        try {
            return jdbcTemplate.queryForObject(sql,
                    (rs, rowNum) -> new User(
                         rs.getString("name"),
                         rs.getString("email"),
                         rs.getString("address"),
                         rs.getInt("points")
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
          }
    }

    public void delete(String email) {
        String sql = """
                DELETE FROM users WHERE email = ?;
                """;
        jdbcTemplate.update(sql, email);
    }

}
