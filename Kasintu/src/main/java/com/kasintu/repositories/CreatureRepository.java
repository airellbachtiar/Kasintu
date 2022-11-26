package com.kasintu.repositories;

import com.kasintu.repositories.entities.Creature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreatureRepository extends JpaRepository<Creature, String> {
    
    List<Creature> findAllByRarity_RarityIDOrderByName(String rarityID);

    boolean existsByName(String name);
}
