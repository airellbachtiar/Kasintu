package com.kasintu.services.inventoryservices.impl;

import com.kasintu.repositories.InventoryRepository;
import com.kasintu.services.inventoryservices.exception.InvalidInventoryException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryIDValidatorImplTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryIDValidatorImpl inventoryIDValidator;

    @Test
    void validateID_Successful()
    {
        String inventoryID = "1";

        when(inventoryRepository.existsById(inventoryID)).thenReturn(true);
        inventoryIDValidator.validateID(inventoryID);

        verify(inventoryRepository).existsById(inventoryID);
    }

    @Test
    void validateID_IDNotFound_ThrowException()
    {
        String inventoryID = "1";

        when(inventoryRepository.existsById(inventoryID)).thenReturn(false);

        assertThrows(InvalidInventoryException.class, () -> inventoryIDValidator.validateID(inventoryID));
    }
}