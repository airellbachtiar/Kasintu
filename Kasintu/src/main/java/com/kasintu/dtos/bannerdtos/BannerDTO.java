package com.kasintu.dtos.bannerdtos;

import com.kasintu.dtos.pullratedtos.PullRateDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Generated
public class BannerDTO {
    private String bannerID;
    private int cost;
    private List<PullRateDTO> pullRates;
}
