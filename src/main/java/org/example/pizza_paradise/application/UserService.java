package org.example.pizza_paradise.application;

import org.example.pizza_paradise.domain.IUserRepository;
import org.example.pizza_paradise.domain.Pizza;
import org.example.pizza_paradise.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final IUserRepository uRepo;

    public UserService(IUserRepository uRepo){
        this.uRepo = uRepo;
    }


    public void createUser(User user) {
        user.setName(user.getName());
        user.setEmail(user.getEmail());
        uRepo.save(user);
    }


    public User login(String mail) {
        return null;
    }
}
