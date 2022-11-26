package com.kasintu.repositories.entities;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "rarities")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Rarity {

    @Id
    @Column(name = "rarity_id")
    private String rarityID;

    @NotNull
    @Length(max = 20)
    @Column(name = "type")
    private String rarityType;

    @Column(name = "level")
    private Integer rarityLevel;
}
