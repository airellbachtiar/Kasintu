package com.kasintu.repositories;

import com.kasintu.repositories.entities.Player;
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
class PlayerRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void findByUser_UserID_ReturnPlayerByUserID()
    {
        User user = User.builder()
                .userID("1")
                .email("email")
                .username("username")
                .password("password")
                .startDate(LocalDate.now())
                .build();
        entityManager.persist(user);

        Player player = Player.builder()
                .playerID("2")
                .user(user)
                .coin(1000)
                .build();
        entityManager.persist(player);

        Player actualResponse = playerRepository.findByUser_UserID("1");

        assertEquals(player, actualResponse);
    }
}