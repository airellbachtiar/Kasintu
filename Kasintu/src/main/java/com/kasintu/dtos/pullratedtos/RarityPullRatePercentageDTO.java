package com.kasintu.dtos.pullratedtos;

import com.kasintu.repositories.entities.Rarity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Generated
public class RarityPullRatePercentageDTO {
    private Rarity rarity;
    private String percentage;
}
