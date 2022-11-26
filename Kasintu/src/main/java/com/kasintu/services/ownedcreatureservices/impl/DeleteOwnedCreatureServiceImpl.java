package com.kasintu.services.ownedcreatureservices.impl;

import com.kasintu.repositories.OwnedCreatureRepository;
import com.kasintu.services.ownedcreatureservices.DeleteOwnedCreatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteOwnedCreatureServiceImpl implements DeleteOwnedCreatureService {

    private final OwnedCreatureRepository ownedCreatureRepository;

    @Override
    public void deleteOwnedCreature(String ownedCreatureID) {
        ownedCreatureRepository.deleteById(ownedCreatureID);
    }
}
