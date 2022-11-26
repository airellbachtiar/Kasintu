package com.kasintu.repositories;

import com.kasintu.repositories.entities.Rarity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RarityRepository extends JpaRepository<Rarity, String> {

    boolean existsByRarityType(String rarityType);
}
