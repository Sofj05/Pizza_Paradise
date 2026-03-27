package org.example.pizza_paradise.infrastructure;

import org.example.pizza_paradise.domain.IOrderRepository;
import org.example.pizza_paradise.domain.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public class JdbcOrderRepository implements IOrderRepository {

    private JdbcTemplate jdbcTemplate;
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Order order) {
        String sql = """
                INSERT INTO ORDERS(user_email,totalPrice,created_at)
                Values (?,?,?)
                """;
        jdbcTemplate.update(sql, ps -> {
            ps.setString(1, order.getUser().getEmail());
            ps.setDouble(2,order.getTotalPrice());
            ps.setDate(3, (Date) order.getDate());
        });
    }

}
