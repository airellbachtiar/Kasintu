package com.kasintu.dtos.bannerdtos;

import com.kasintu.repositories.entities.PullRate;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class BannerCheckResponseDTO
{
    private List<PullRate> pullRates;
}
