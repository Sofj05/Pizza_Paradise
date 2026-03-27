package org.example.pizza_paradise;

import org.example.pizza_paradise.application.UserService;
import org.example.pizza_paradise.application.Validation.Validation;
import org.example.pizza_paradise.domain.IPizzaRepository;
import org.example.pizza_paradise.domain.IUserRepository;
import org.example.pizza_paradise.domain.User;
import org.example.pizza_paradise.infrastructure.JdbcPizzaRepository;
import org.example.pizza_paradise.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService userService;
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private final IUserRepository userRepo = new JdbcUserRepository(jdbcTemplate);
    private final IPizzaRepository pizzaRepo = new JdbcPizzaRepository(jdbcTemplate);
    private final Validation validation = new Validation(userRepo, pizzaRepo);
    private final User user = new User("Mathias", "Mathias@mail.com", "Tøpkildevej 63");

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepo, validation);
    }

    @Test
    void ShouldAddBonusPointsToUser() {
        user.addBonusPoints(10);
        assertEquals(10, user.getPoints());
    }

    @Test
    void ShouldNotAllowNegativeBonusPoints() {
        User user = new User("Mathias", "Mathias@mail.com", "Tøpkildevej 63");
        assertThrows(IllegalArgumentException.class, ()-> {
            user.addBonusPoints(-10);
        });
    }

    @Test
    void shouldCreateUser() {
        IUserRepository userRepository = mock(IUserRepository.class);
        userService.createUser(user);
        verify(userRepository).save(any(User.class));
    }
//    @Test
//    void shouldLoginUserWithCorrectCredentials() {
//        IUserRepository userRepository = mock(IUserRepository.class);
//        User user = new User("Mathias", "Mathias@mail.com", "Tøpkildevej 63");
//
//        when(userRepository.findByEmail("Mathias@mail.com"))
//                .thenReturn(user);
//
//        UserService userService = new UserService(userRepository);
//
//        User result = userService.login("Mathias@mail.com");
//
//        assertEquals(user, result);
//    }






}
