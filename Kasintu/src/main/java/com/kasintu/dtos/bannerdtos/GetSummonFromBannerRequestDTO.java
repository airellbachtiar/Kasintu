package com.kasintu.dtos.bannerdtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class GetSummonFromBannerRequestDTO {
    private String bannerID;
    private Integer summonTimes;
}
