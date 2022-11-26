package com.kasintu.repositories.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pull_rates")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class PullRate {

    @Id
    @Column(name = "pull_rate_id")
    private String pullRateID;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "rarity_id", referencedColumnName = "rarity_id")
    private Rarity rarity;

    @NotNull
    @Column(name = "pull_rate")
    private Integer rateValue;
}
