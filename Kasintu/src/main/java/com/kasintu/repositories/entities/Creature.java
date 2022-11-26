package com.kasintu.repositories.entities;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "creatures")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Creature {

    @Id
    @Column(name = "creature_id")
    private String creatureID;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "rarity_id", referencedColumnName = "rarity_id")
    private Rarity rarity;

    @NotNull
    @Length(max = 50)
    @Column(name = "name")
    private String name;

    @Length(max = 400)
    @Column(name = "description")
    private String description;
}
