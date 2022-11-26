package com.kasintu.services.creatureservices.impl;

import com.kasintu.repositories.CreatureRepository;
import com.kasintu.services.creatureservices.DeleteCreatureService;
import com.kasintu.services.creatureservices.exception.InvalidCreatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteCreatureServiceImpl implements DeleteCreatureService {

    private final CreatureRepository creatureRepository;

    @Transactional
    @Override
    public void deleteCreature(String creatureID)
    {
        if(!creatureRepository.existsById(creatureID))
        {
            throw new InvalidCreatureException();
        }
        creatureRepository.deleteById(creatureID);
    }
}
