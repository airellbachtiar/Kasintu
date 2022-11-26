package com.kasintu.repositories;

import com.kasintu.repositories.entities.Creature;
import com.kasintu.repositories.entities.Rarity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreatureRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CreatureRepository creatureRepository;

    private Rarity saveRarity(String rarityID, String rarityType, Integer rarityLevel)
    {
        Rarity rarity= Rarity.builder().rarityID(rarityID).rarityType(rarityType).rarityLevel(rarityLevel).build();
        entityManager.persist(rarity);
        return rarity;
    }

    @Test
    void findAllByRarity_RarityIDOrderByName_ReturnCreaturesWithSameRarity()
    {
        Rarity common = saveRarity("1", "Common", 0);
        Rarity uncommon = saveRarity("2", "Uncommon", 1);

        Creature creature1 = Creature.builder()
                .creatureID("1")
                .name("Creature1")
                .rarity(common)
                .description("none")
                .build();
        entityManager.persist(creature1);

        Creature creature2 = Creature.builder()
                .creatureID("2")
                .name("Creature2")
                .rarity(common)
                .description("none")
                .build();
        entityManager.persist(creature2);

        Creature creature3 = Creature.builder()
                .creatureID("3")
                .name("Creature3")
                .rarity(uncommon)
                .description("none")
                .build();
        entityManager.persist(creature3);

        List<Creature> actualCreatures = creatureRepository.findAllByRarity_RarityIDOrderByName("1");
        List<Creature> expectedCreatures = List.of(creature1, creature2);

        assertEquals(expectedCreatures, actualCreatures);
    }

    @Test
    void existsByName_ReturnTrue()
    {
        Rarity common = saveRarity("1", "Common", 0);

        Creature creature = Creature.builder()
                .creatureID("1")
                .name("Creature")
                .rarity(common)
                .description("none")
                .build();
        entityManager.persist(creature);

        boolean actual = creatureRepository.existsByName("Creature");
        assertTrue(actual);
    }

    @Test
    void existsByName_ReturnFalse()
    {
        boolean actual = creatureRepository.existsByName("Creature");
        assertFalse(actual);
    }
}