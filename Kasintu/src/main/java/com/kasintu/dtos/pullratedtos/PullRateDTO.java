package com.kasintu.dtos.pullratedtos;

import com.kasintu.dtos.raritydtos.RarityDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
@Generated
public class PullRateDTO {
    private String pullRateID;
    private RarityDTO rarity;
    private int rateValue;
}
