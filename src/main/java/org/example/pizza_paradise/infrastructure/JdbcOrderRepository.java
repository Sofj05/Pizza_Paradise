package org.example.pizza_paradise.infrastructure;

import org.example.pizza_paradise.domain.IOrderRepository;
import org.example.pizza_paradise.domain.Order;
import org.example.pizza_paradise.domain.Pizza;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class JdbcOrderRepository implements IOrderRepository {

    private JdbcTemplate jdbcTemplate;
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Order order) {
        int orderId = insertOrder(order);
        insertOrderPizzas(orderId,order.getPizzas());
    }

    public int insertOrder(Order order) {
        String sql = """
                INSERT INTO ORDERS(user_email,totalPrice,created_at)
                Values (?,?,?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(Connection -> {
            PreparedStatement ps = Connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,order.getUser().getEmail());
            ps.setDouble(2,order.getTotalPrice());
            ps.setDate(3,Date.valueOf(order.getDate()));
            return ps;
        },keyHolder);
        return  Objects.requireNonNull(keyHolder.getKey()).intValue();
    }
    private void insertOrderPizzas(int orderId, List<Pizza> pizzas) {
        String sql = "INSERT INTO order_pizzas (order_id, pizza_id) VALUES (?, ?)";
        for (Pizza pizza : pizzas) jdbcTemplate.update(sql, orderId, pizza.getId());
    }





}
