package org.example.pizza_paradise.application;

import org.example.pizza_paradise.domain.IUserRepository;
import org.example.pizza_paradise.domain.Pizza;
import org.example.pizza_paradise.domain.User;

public class UserService {

    private IUserRepository uRepo;

    public UserService(IUserRepository uRepo){
        this.uRepo = uRepo;
    }


    public void createUser(String name, String email) {
    }

    public User login(String mail) {
        return null;
    }
}
