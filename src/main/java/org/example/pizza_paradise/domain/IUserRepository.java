package org.example.pizza_paradise.domain;

public interface IUserRepository {

    void save(User user);

    User findByEmail(String mail);
}
