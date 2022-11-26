package com.kasintu.repositories;

import com.kasintu.repositories.entities.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OwnedCreatureRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private OwnedCreatureRepository ownedCreatureRepository;

    private Rarity saveRarity(String rarityID, String rarityType, Integer rarityLevel)
    {
        Rarity rarity= Rarity.builder().rarityID(rarityID).rarityType(rarityType).rarityLevel(rarityLevel).build();
        entityManager.persist(rarity);
        return rarity;
    }

    private Creature saveCreature(String creatureID, String name, Rarity rarity)
    {
        Creature creature = Creature.builder()
                .creatureID(creatureID)
                .name(name)
                .rarity(rarity)
                .description("none")
                .build();
        entityManager.persist(creature);
        return creature;
    }

    private User saveUser(String userID, String username)
    {
        User user = User.builder()
                .userID(userID)
                .email("email")
                .username(username)
                .password("password")
                .startDate(LocalDate.now())
                .build();
        entityManager.persist(user);
        return user;
    }

    private Player savePlayer(String playerID, User user)
    {
        Player player = Player.builder()
                .playerID(playerID)
                .user(user)
                .coin(1000)
                .build();
        entityManager.persist(player);
        return player;
    }

    private Inventory saveInventory(String inventoryID, Player player)
    {
        Inventory inventory = Inventory.builder()
                .inventoryID(inventoryID)
                .ownedCreatures(List.of())
                .player(player)
                .build();
        entityManager.persist(inventory);
        return inventory;
    }

    @Test
    void findAllByInventory_InventoryID()
    {

        //Make creatures
        Rarity common = saveRarity("1", "Common", 0);
        Rarity uncommon = saveRarity("2", "Uncommon", 1);
        Creature creature1 = saveCreature("1", "Creature1", common);
        Creature creature2 = saveCreature("2", "Creature2", common);
        Creature creature3 = saveCreature("3", "Creature3", uncommon);

        //Make Inventories
        User user1 = saveUser("1", "username1");
        User user2 = saveUser("2", "username2");

        Player player1 = savePlayer("3", user1);
        Player player2 = savePlayer("4", user2);

        Inventory inventory1 = saveInventory("5", player1);

        Inventory inventory2 = saveInventory("6", player2);

        //Create Owned Creature
        OwnedCreature ownedCreature1 = OwnedCreature.builder()
                .ownedCreatureID("7")
                .inventory(inventory1)
                .creature(creature1)
                .build();
        entityManager.persist(ownedCreature1);

        OwnedCreature ownedCreature2 = OwnedCreature.builder()
                .ownedCreatureID("8")
                .inventory(inventory1)
                .creature(creature2)
                .build();
        entityManager.persist(ownedCreature2);

        OwnedCreature ownedCreature3 = OwnedCreature.builder()
                .ownedCreatureID("9")
                .inventory(inventory2)
                .creature(creature3)
                .build();
        entityManager.persist(ownedCreature3);

        List<OwnedCreature> actualResponse = ownedCreatureRepository.findAllByInventory_InventoryID("5");
        List<OwnedCreature> expectedResponse = List.of(ownedCreature1, ownedCreature2);

        assertEquals(expectedResponse, actualResponse);
    }
}