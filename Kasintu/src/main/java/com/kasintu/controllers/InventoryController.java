package com.kasintu.controllers;

import com.kasintu.configs.security.isauthenticated.IsAuthenticated;
import com.kasintu.dtos.inventorydtos.GetAllInventoriesResponseDTO;
import com.kasintu.dtos.inventorydtos.InventoryDTO;
import com.kasintu.services.inventoryservices.GetAllInventoriesService;
import com.kasintu.services.inventoryservices.impl.GetInventoryByInventoryIDServiceImpl;
import com.kasintu.services.inventoryservices.impl.GetInventoryByUserIDServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RestController
@RequestMapping("/inventory")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class InventoryController {

    private final GetAllInventoriesService getAllInventoriesService;
    private final GetInventoryByInventoryIDServiceImpl getInventoryByInventoryIDService;
    private final GetInventoryByUserIDServiceImpl getInventoryByPlayerIDService;

    @IsAuthenticated
    @GetMapping()
    public ResponseEntity<GetAllInventoriesResponseDTO> getAllInventory()
    {
        return ResponseEntity.ok().body(getAllInventoriesService.getAllInventories());
    }

    @IsAuthenticated
    @GetMapping("{inventoryID}")
    public ResponseEntity<InventoryDTO> getInventoryByInventoryID(@PathVariable String inventoryID)
    {
        Optional<InventoryDTO> inventoryDTO = getInventoryByInventoryIDService.getInventory(inventoryID);
        if(inventoryDTO.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(inventoryDTO.get());
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_PLAYER"})
    @GetMapping("/player/{userID}")
    public ResponseEntity<InventoryDTO> getInventoryByUserID(@PathVariable String userID)
    {
        Optional<InventoryDTO> inventoryDTO = getInventoryByPlayerIDService.getInventory(userID);
        if(inventoryDTO.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(inventoryDTO.get());
    }
}
