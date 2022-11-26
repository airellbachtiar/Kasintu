package com.kasintu.dtos.pullratedtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class UpdatePullRateRequestDTO {
    private String pullRateID;
    private String rarityID;
    private int rateValue;
}
