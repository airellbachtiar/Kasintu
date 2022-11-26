package com.kasintu.repositories;

import com.kasintu.repositories.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
    Player findByUser_UserID(String userID);
}
