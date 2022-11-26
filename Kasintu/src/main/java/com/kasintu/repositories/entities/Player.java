package com.kasintu.repositories.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "players")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Player {

    @Id
    @Column(name = "player_id")
    private String playerID;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @NotNull
    @Column(name = "coin")
    private Integer coin;
}
