package com.kasintu.repositories;

import com.kasintu.repositories.entities.Inventory;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class InventoryRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Test
    void findByPlayer_PlayerID_ReturnInventoryByPlayerID()
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

        Inventory inventory = Inventory.builder()
                .inventoryID("3")
                .ownedCreatures(List.of())
                .player(player)
                .build();
        entityManager.persist(inventory);

        Optional<Inventory> actualResponse = inventoryRepository.findByPlayer_PlayerID("2");

        assertEquals(inventory, actualResponse.orElseThrow());
    }
}