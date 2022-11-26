package com.kasintu.dtos.pullratedtos;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class GetAllPullRatesResponseDTO {
    List<PullRateDTO> pullRates;
}
