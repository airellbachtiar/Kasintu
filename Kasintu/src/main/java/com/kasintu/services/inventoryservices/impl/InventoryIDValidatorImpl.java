package com.kasintu.services.inventoryservices.impl;

import com.kasintu.repositories.InventoryRepository;
import com.kasintu.services.inventoryservices.InventoryIDValidator;
import com.kasintu.services.inventoryservices.exception.InvalidInventoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryIDValidatorImpl implements InventoryIDValidator
{

    private final InventoryRepository inventoryRepository;

    @Override
    public void validateID(String inventoryID)
    {
        if(inventoryID == null || !inventoryRepository.existsById(inventoryID))
        {
            throw new InvalidInventoryException();
        }
    }
}
