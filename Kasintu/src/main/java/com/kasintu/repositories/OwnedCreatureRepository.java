package com.kasintu.repositories;

import com.kasintu.repositories.entities.OwnedCreature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnedCreatureRepository extends JpaRepository<OwnedCreature, String>
{
    List<OwnedCreature> findAllByInventory_InventoryID(String inventoryID);
}
