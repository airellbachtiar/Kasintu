package com.kasintu.repositories;

import com.kasintu.repositories.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin findByUser_UserID(String userID);
}
