package org.example.pizza_paradise.infrastructure;

import org.example.pizza_paradise.domain.Enum.Toppings;
import org.example.pizza_paradise.domain.IPizzaRepository;
import org.example.pizza_paradise.domain.Pizza;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcPizzaRepository implements IPizzaRepository {

    private JdbcTemplate jdbcTemplate;
    public JdbcPizzaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Pizza pizza){
        String sql = """
                insert into pizzas(name, price, description)
                values (?, ?, ?)
                """;

        jdbcTemplate.update(sql, ps -> {
            ps.setString(1, pizza.getName());
            ps.setDouble(2, pizza.getPrice());
            ps.setString(3, pizza.getDescription());
        });

    }

    public Pizza findPizzaById(int id) {
        String sql = """
            SELECT * FROM pizzas WHERE id= ?;
            """;
        try {
            return jdbcTemplate.queryForObject(sql,
                    (rs, rowNum) -> new Pizza(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getString("description")

                    ),
                    id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Pizza> findAll(){
        String sql = """
                SELECT 
                    p.id AS pizza_id,
                    p.name AS pizza_name,
                    p.price AS pizza_price,
                    p.description AS pizza_description,
                    t.name AS topping_name
                    FROM Pizzas p
                    LEFT JOIN Pizza_Toppings pt ON p.id = pt.pizza_id
                    LEFT JOIN Toppings t ON pt.topping_id = t.id
                    ORDER BY p.id;
        """;

        //Map til at holde styr på de forskellige pizzaer og sørger for at der ikke kommer duplikation af den samme pizza med forskellige toppings
        Map<Integer, Pizza> pizzaMap = new LinkedHashMap<>();

        jdbcTemplate.query(sql, rs -> {
            int pizzaId = rs.getInt("pizza_id");

            Pizza pizza = pizzaMap.get(pizzaId);

            //Tilføjer til listen hvis pizzaen ikke allerede er der
            if (pizza == null) {
                pizza = new Pizza(
                        pizzaId,
                        rs.getString("pizza_name"),
                        rs.getInt("pizza_price"),
                        new ArrayList<>(), //Ingen toppings
                        rs.getString("pizza_description")
                );
                pizzaMap.put(pizzaId, pizza);
            }

            String toppingName = rs.getString("topping_name");

            if (toppingName != null) {
                Toppings topping = Toppings.valueOf(toppingName.toUpperCase());
                pizza.getToppingsList().add(topping);
            }
        });

        return new ArrayList<>(pizzaMap.values());
    }

}
