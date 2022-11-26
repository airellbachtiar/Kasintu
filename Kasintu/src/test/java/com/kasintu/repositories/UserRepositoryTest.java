package com.kasintu.repositories;

import com.kasintu.repositories.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void existsByUsername_ReturnTrue()
    {
        User user = User.builder()
                .userID("1")
                .email("email")
                .username("username")
                .password("password")
                .startDate(LocalDate.now())
                .build();
        entityManager.persist(user);

        boolean actualResponse = userRepository.existsByUsername("username");
        assertTrue(actualResponse);
    }

    @Test
    void existsByUsername_ReturnFalse()
    {
        boolean actualResponse = userRepository.existsByUsername("username");
        assertFalse(actualResponse);
    }

    @Test
    void findByUsername()
    {
        User user = User.builder()
                .userID("1")
                .email("email")
                .username("username")
                .password("password")
                .startDate(LocalDate.now())
                .build();
        entityManager.persist(user);

        User actualUser = userRepository.findByUsername("username");

        assertEquals(user, actualUser);
    }
}