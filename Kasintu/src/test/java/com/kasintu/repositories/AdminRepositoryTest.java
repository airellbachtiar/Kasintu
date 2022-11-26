package com.kasintu.repositories;

import com.kasintu.repositories.entities.Admin;
import com.kasintu.repositories.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AdminRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AdminRepository adminRepository;

    @Test
    void findByUser_UserID_ReturnAdminByUserID()
    {
        User user = User.builder()
                .userID("1")
                .email("email")
                .username("username")
                .password("password")
                .startDate(LocalDate.now())
                .build();
        entityManager.persist(user);

        Admin admin = Admin.builder()
                .adminID("2")
                .user(user)
                .build();
        entityManager.persist(admin);

        Admin actualResponse = adminRepository.findByUser_UserID("1");

        assertEquals(admin, actualResponse);
    }
}