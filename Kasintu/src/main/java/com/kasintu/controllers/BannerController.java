package com.kasintu.controllers;

import com.kasintu.configs.security.isauthenticated.IsAuthenticated;
import com.kasintu.dtos.bannerdtos.*;
import com.kasintu.services.bannerservices.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/banner")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class BannerController
{

    private final CreateBannerService createBannerService;
    private final DeleteBannerService deleteBannerService;
    private final GetAllBannersService getAllBannersService;
    private final GetBannerService getBannerService;
    private final UpdateBannerService updateBannerService;
    private final BannerPullRatesInformationService bannerPullRatesInformationService;

    @GetMapping()
    public ResponseEntity<GetAllBannersResponseDTO> getAllBanners()
    {
        return ResponseEntity.ok(getAllBannersService.getAllBanners());
    }

    @GetMapping("{bannerID}")
    public ResponseEntity<BannerDTO> getBanner(@PathVariable String bannerID)
    {
        Optional<BannerDTO> bannerDTO = getBannerService.getBanner(bannerID);
        if(bannerDTO.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(bannerDTO.get());
    }

    @GetMapping("/pullRateInfo/{bannerID}")
    public ResponseEntity<BannerPullRatesInformationResponseDTO> getBannerPullRateInformation(@PathVariable String bannerID)
    {
        return ResponseEntity.ok().body(bannerPullRatesInformationService.bannerPullRatesInformationService(bannerID));
    }

    @IsAuthenticated
    @PostMapping()
    public ResponseEntity<CreateBannerResponseDTO> createBanner(@RequestBody CreateBannerRequestDTO request)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(createBannerService.createBanner(request));
    }

    @IsAuthenticated
    @DeleteMapping("{bannerID}")
    public ResponseEntity<Void> deleteBanner(@PathVariable String bannerID)
    {
        deleteBannerService.deleteBanner(bannerID);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @PutMapping("{bannerID}")
    public ResponseEntity<Void> updateBanner(@PathVariable String bannerID, @RequestBody UpdateBannerRequestDTO request)
    {
        request.setBannerID(bannerID);
        updateBannerService.updateBanner(request);
        return ResponseEntity.noContent().build();
    }


}
