package com.kasintu.dtos.pullratedtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class CreatePullRateRequestDTO {
    private String rarityID;
    private int rateValue;
}
