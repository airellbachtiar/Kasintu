package com.kasintu.repositories;

import com.kasintu.repositories.entities.Rarity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RarityRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private RarityRepository rarityRepository;

    @Test
    void existsByRarityType_Found_ReturnTrue()
    {
        Rarity common = Rarity.builder()
                .rarityID("1")
                .rarityType("Common")
                .rarityLevel(0)
                .build();
        entityManager.persist(common);

        boolean actualResult = rarityRepository.existsByRarityType("Common");
        assertTrue(actualResult);
    }

    @Test
    void existsByRarityType_NotFound_ReturnFalse()
    {
        Rarity common = Rarity.builder()
                .rarityID("1")
                .rarityType("Common")
                .rarityLevel(0)
                .build();
        entityManager.persist(common);

        boolean actualResult = rarityRepository.existsByRarityType("None");
        assertFalse(actualResult);
    }
}