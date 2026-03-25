package org.example.pizza_paradise.infrastructure;

import org.example.pizza_paradise.domain.IUserRepository;
import org.example.pizza_paradise.domain.User;

public class JdbcUserRepository implements IUserRepository {

    public void save(User user) {
    }

    public User findByEmail(String email) {
        return null;
    }

}
