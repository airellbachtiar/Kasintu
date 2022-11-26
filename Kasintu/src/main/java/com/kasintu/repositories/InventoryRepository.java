package com.kasintu.repositories;

import com.kasintu.repositories.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
    Optional<Inventory> findByPlayer_PlayerID(String playerID);
}
