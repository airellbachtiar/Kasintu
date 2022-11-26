package com.kasintu.dtos.bannerdtos;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class GetAllBannersResponseDTO
{
    private List<BannerDTO> banners;
}
