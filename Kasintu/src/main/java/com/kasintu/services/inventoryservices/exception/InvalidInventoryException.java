package com.kasintu.services.inventoryservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidInventoryException extends ResponseStatusException
{
    public InvalidInventoryException()
    {
        super(HttpStatus.BAD_REQUEST, "INVENTORY_INVALID");
    }
}
