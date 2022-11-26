package com.kasintu.dtos.bannerdtos;

import com.kasintu.dtos.pullratedtos.RarityPullRatePercentageDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Generated
public class BannerPullRatesInformationResponseDTO {
    private List<RarityPullRatePercentageDTO> ratesInformation;
}
