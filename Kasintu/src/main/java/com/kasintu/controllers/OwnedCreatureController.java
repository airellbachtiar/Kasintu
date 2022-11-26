package com.kasintu.controllers;

import com.kasintu.configs.security.isauthenticated.IsAuthenticated;
import com.kasintu.dtos.ownedcreatureddtos.GetAllOwnedCreaturesRequestDTO;
import com.kasintu.dtos.ownedcreatureddtos.GetAllOwnedCreaturesResponseDTO;
import com.kasintu.dtos.ownedcreatureddtos.OwnedCreatureDTO;
import com.kasintu.dtos.ownedcreatureddtos.UpdateOwnedCreatureRequestDTO;
import com.kasintu.services.ownedcreatureservices.DeleteOwnedCreatureService;
import com.kasintu.services.ownedcreatureservices.GetAllOwnedCreaturesService;
import com.kasintu.services.ownedcreatureservices.GetOwnedCreatureService;
import com.kasintu.services.ownedcreatureservices.UpdateOwnedCreatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ownedCreature")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class OwnedCreatureController
{
    private final GetAllOwnedCreaturesService getAllOwnedCreaturesService;
    private final GetOwnedCreatureService getOwnedCreatureService;
    private final UpdateOwnedCreatureService updateOwnedCreatureService;
    private final DeleteOwnedCreatureService deleteOwnedCreatureService;

    @IsAuthenticated
    @GetMapping
    public ResponseEntity<GetAllOwnedCreaturesResponseDTO> getAllOwnedCreaturesResponseDTOResponseEntity(@RequestParam(value = "inventoryID", required = false) String inventoryID)
    {
        return ResponseEntity.ok().body(getAllOwnedCreaturesService.getAllOwnedCreatures(
                        GetAllOwnedCreaturesRequestDTO.builder()
                                .inventoryID(inventoryID)
                                .build()));
    }

    @IsAuthenticated
    @GetMapping("{ownedCreatureID}")
    public ResponseEntity<OwnedCreatureDTO> getOwnedCreature(@PathVariable String ownedCreatureID)
    {
        Optional<OwnedCreatureDTO> ownedCreatureDTO = getOwnedCreatureService.getOwnedCreature(ownedCreatureID);
        if(ownedCreatureDTO.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ownedCreatureDTO.get());
    }

    @IsAuthenticated
    @DeleteMapping("{ownedCreatureID}")
    public ResponseEntity<Void> deleteCreature(@PathVariable String ownedCreatureID)
    {
        deleteOwnedCreatureService.deleteOwnedCreature(ownedCreatureID);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @PutMapping("{ownedCreatureID}")
    public ResponseEntity<Void> updateOwnedCreature(@PathVariable("ownedCreatureID") String ownedCreatureID, @RequestBody UpdateOwnedCreatureRequestDTO request)
    {
        request.setOwnedCreatureID(ownedCreatureID);
        updateOwnedCreatureService.updateOwnedCreature(request);
        return ResponseEntity.noContent().build();
    }
}
