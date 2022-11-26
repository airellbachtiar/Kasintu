package com.kasintu.services.inventoryservices;

import com.kasintu.services.inventoryservices.exception.InvalidInventoryException;

public interface InventoryIDValidator {
    void validateID(String inventoryID) throws InvalidInventoryException;
}
