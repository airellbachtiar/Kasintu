package com.kasintu.repositories.entities;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class User {

    @Id
    @Column(name = "user_id")
    private String userID;

    @NotBlank
    @Length(max = 20)
    @Column(name = "username")
    private String username;

    @Column(name = "email")
    @Length(max = 100)
    private String email;

    @Column(name = "password")
    @Length(max = 100)
    private String password;

    @Column(name = "start_date")
    private LocalDate startDate;
}
