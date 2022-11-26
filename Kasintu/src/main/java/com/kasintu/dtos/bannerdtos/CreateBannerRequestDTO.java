package com.kasintu.dtos.bannerdtos;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class CreateBannerRequestDTO
{
    private int cost;
    private List<String> pullRateIDs;
}
