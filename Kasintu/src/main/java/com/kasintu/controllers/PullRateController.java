package com.kasintu.controllers;

import com.kasintu.configs.security.isauthenticated.IsAuthenticated;
import com.kasintu.dtos.pullratedtos.*;
import com.kasintu.services.pullrateservices.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pullRates")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class PullRateController {

    private final CreatePullRateService createPullRateService;
    private final DeletePullRateService deletePullRateService;
    private final GetAllPullRatesService getAllPullRatesService;
    private final GetPullRateService getPullRateService;
    private final UpdatePullRateService updatePullRateService;

    @GetMapping
    public ResponseEntity<GetAllPullRatesResponseDTO> getAllPullRates()
    {
        return ResponseEntity.ok().body(getAllPullRatesService.getAllPullRates());
    }

    @GetMapping("{pullRateID}")
    public ResponseEntity<PullRateDTO> getPullRate(@PathVariable String pullRateID)
    {
        Optional<PullRateDTO> pullRateDTO = getPullRateService.getPullRate(pullRateID);
        if(pullRateDTO.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(pullRateDTO.get());
    }

    @IsAuthenticated
    @PostMapping()
    public ResponseEntity<CreatePullRateResponseDTO> createPullRate(@RequestBody CreatePullRateRequestDTO request)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(createPullRateService.createPullRate(request));
    }

    @IsAuthenticated
    @DeleteMapping("{pullRateID}")
    public ResponseEntity<Void> deletePullRate(@PathVariable String pullRateID)
    {
        deletePullRateService.deletePullRate(pullRateID);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @PutMapping("{pullRateID}")
    public ResponseEntity<Void> updatePullRate(@PathVariable String pullRateID, @RequestBody UpdatePullRateRequestDTO request)
    {
        request.setPullRateID(pullRateID);
        updatePullRateService.updatePullRate(request);
        return ResponseEntity.noContent().build();
    }
}
