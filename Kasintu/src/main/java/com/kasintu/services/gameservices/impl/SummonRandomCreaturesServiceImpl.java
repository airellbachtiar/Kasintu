package com.kasintu.services.gameservices.impl;

import com.kasintu.dtos.bannerdtos.BannerDTO;
import com.kasintu.dtos.creaturedtos.CreatureDTO;
import com.kasintu.dtos.creaturedtos.GetAllCreaturesRequestDTO;
import com.kasintu.dtos.raritydtos.RarityDTO;
import com.kasintu.services.creatureservices.GetAllCreaturesService;
import com.kasintu.services.creatureservices.GetCreatureService;
import com.kasintu.services.gameservices.SummonCreaturesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SummonRandomCreaturesServiceImpl implements SummonCreaturesService {

    private final GetCreatureService getCreatureService;
    private final GetAllCreaturesService getAllCreaturesService;

    @Transactional
    @Override
    public List<CreatureDTO> getCreaturesFromBanner(BannerDTO banner, int times)
    {
        List<CreatureDTO> obtainedCreatures = new ArrayList<>();

        //Logic for summoning once
        Map<Integer, RarityDTO> mapRarity = new HashMap<>();
        int totalRoll = 0;
        int mapLocation = 0;

        //Goes through each rarity pull rate list
        for(int i = 0; i < banner.getPullRates().size(); i++)
        {
            //add how much rate for this rarity
            totalRoll += banner.getPullRates().get(i).getRateValue();
            for(int j = 0; j < banner.getPullRates().get(i).getRateValue(); j++)
            {
                //put this rarity inside map
                mapRarity.put(mapLocation, banner.getPullRates().get(i).getRarity());
                mapLocation++;
            }
        }

        //Get creature based on pull rate
        for(int time = 0; time < times; time++)
        {
            //20% of getting a creature
            SecureRandom random = new SecureRandom();
            if(random.nextInt(5)==0)
            {
                obtainedCreatures.add(getRandomCreatureFromPreMapBanner(totalRoll, mapRarity));
            }
            else
            {
                obtainedCreatures.add(null);
            }
        }

        return obtainedCreatures;
    }

    private CreatureDTO getRandomCreatureFromPreMapBanner(int totalRoll, Map<Integer, RarityDTO> mapRarity)
    {
        SecureRandom random = new SecureRandom();
        int randomNumber = random.nextInt(totalRoll);

        //get rarity from map
        RarityDTO chosenRarity = mapRarity.get(randomNumber);

        //get random Creature from the chosen rarity
        List<CreatureDTO> possibleCreatureToObtain = getAllCreaturesService.getAllCreatures(GetAllCreaturesRequestDTO.builder().rarityID(chosenRarity.getRarityID()).build()).getCreatures();
        randomNumber = random.nextInt(possibleCreatureToObtain.size());

        Optional<CreatureDTO> obtainedCreature = getCreatureService.getCreature(possibleCreatureToObtain.get(randomNumber).getCreatureID());

        if(obtainedCreature.isEmpty())
        {
            return getRandomCreatureFromPreMapBanner(totalRoll, mapRarity);
        }
        return obtainedCreature.get();
    }
}
