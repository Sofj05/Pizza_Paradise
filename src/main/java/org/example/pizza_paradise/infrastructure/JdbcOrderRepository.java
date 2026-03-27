package org.example.pizza_paradise.infrastructure;

import org.example.pizza_paradise.domain.IOrderRepository;
import org.example.pizza_paradise.domain.Order;
import org.example.pizza_paradise.domain.Pizza;
import org.example.pizza_paradise.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

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
            ps.setDate(3, new Date(order.getDate().getTime()));
            return ps;
        },keyHolder);
        return  Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public void insertOrderPizzas(int orderId, List<Pizza> pizzas) {

        String sql = """
        INSERT INTO order_pizzas (order_id, pizza_id, quantity)
        VALUES (?, ?, ?)
        ON DUPLICATE KEY UPDATE quantity = quantity + VALUES(quantity)
        """;
        Map<Integer, Integer> pizzaCounts = new HashMap<>();
        for (Pizza pizza : pizzas) {
            pizzaCounts.merge(pizza.getId(), 1, Integer::sum);
        }

        for (Pizza pizza : pizzas ) {
            jdbcTemplate.update(sql, orderId, pizza.getId(), pizza.getQuantity());
        }
    }

    public List<Order> findOrdersByUserEmail(String email) {
        String sql = """ 
                SELECT 
                    o.id,
                    o.created_at,
                    o.totalPrice,
                    o.user_email,
                    u.name 
                FROM ORDERS o
                    join USERS u ON o.USER_EMAIL = u.email        
                WHERE user_email = ?;
        """;
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setDate(rs.getDate("created_at"));
            order.setTotalPrice(rs.getDouble("totalPrice"));
            User user = new User();
            user.setEmail(rs.getString("user_email"));
            user.setName(rs.getString("name"));

            order.setUser(user);

            List<Pizza> pizzas = findPizzasByOrderId(order.getId());
            order.setPizzas(pizzas);
            return order;
                }, email);
    }
    public List<Pizza> findPizzasByOrderId(int orderId) {

        String sql = """
        SELECT p.id, p.name, p.price, op.quantity
        FROM order_pizzas op
        JOIN pizzas p ON op.pizza_id = p.id
        WHERE op.order_id = ?
    """;
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    Pizza pizza = new Pizza();
                    pizza.setId(rs.getInt("id"));
                    pizza.setName(rs.getString("name"));
                    pizza.setPrice(rs.getDouble("price"));
                    pizza.setQuantity(rs.getInt("quantity"));
                    return pizza;

                }, orderId);
    }



}
