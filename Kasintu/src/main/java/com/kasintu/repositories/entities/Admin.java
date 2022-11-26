package com.kasintu.repositories.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "admins")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Admin {

    @Id
    @Column(name = "admin_id")
    private String adminID;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}
