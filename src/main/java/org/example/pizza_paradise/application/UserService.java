package org.example.pizza_paradise.application;

import org.example.pizza_paradise.application.Validation.Validation;
import org.example.pizza_paradise.domain.IUserRepository;
import org.example.pizza_paradise.domain.Pizza;
import org.example.pizza_paradise.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final IUserRepository uRepo;
    private final Validation validation;

    public UserService(IUserRepository uRepo, Validation validation) {
        this.uRepo = uRepo;
        this.validation = validation;

    }

    public void createUser(User user) {
        uRepo.save(user);
    }


    public User login(String email, String name) {
        if(validation.validateLogin(email, name)){
        return uRepo.findByEmail(email);
        }
        return null;
    }
    public void addPoint(){

    }
}
