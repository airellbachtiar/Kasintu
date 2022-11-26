package com.kasintu.repositories.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "inventories")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Inventory {

    @Id
    @Column(name = "inventory_id")
    private String inventoryID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    private Player player;

    @OneToMany
    @JoinTable(
            name = "owned_creatures",
            joinColumns = @JoinColumn(name = "inventory_id"),
            inverseJoinColumns = @JoinColumn(name = "owned_creature_id")
    )
    private List<OwnedCreature> ownedCreatures;
}
