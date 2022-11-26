package com.kasintu.repositories.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "owned_creatures")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class OwnedCreature {

    @Id
    @Column(name = "owned_creature_id")
    private String ownedCreatureID;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "creature_id", referencedColumnName = "creature_id")
    private Creature creature;
}
