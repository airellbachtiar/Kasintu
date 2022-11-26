package com.kasintu.controllers;

import com.kasintu.configs.security.isauthenticated.IsAuthenticated;
import com.kasintu.dtos.creaturedtos.*;
import com.kasintu.services.creatureservices.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RestController
@RequestMapping("/creatures")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CreatureController {

    private final CreateCreatureService createCreatureService;
    private final DeleteCreatureService deleteCreatureService;
    private final GetAllCreaturesService getAllCreaturesService;
    private final GetCreatureService getCreatureService;
    private final UpdateCreatureService updateCreatureService;

    @GetMapping()
    public ResponseEntity<GetAllCreaturesResponseDTO> getAllCreatures(@RequestParam(value = "rarity", required = false) String rarity)
    {
        GetAllCreaturesRequestDTO request = new GetAllCreaturesRequestDTO();
        request.setRarityID(rarity);

        return ResponseEntity.ok().body(getAllCreaturesService.getAllCreatures(request));
    }

    @GetMapping("{creatureID}")
    public ResponseEntity<CreatureDTO> getCreature(@PathVariable String creatureID)
    {
        Optional<CreatureDTO> creatureDTO = getCreatureService.getCreature(creatureID);
        if(creatureDTO.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(creatureDTO.get());
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping()
    public ResponseEntity<CreateCreatureResponseDTO> createCreature(@RequestBody CreateCreatureRequestDTO request)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(createCreatureService.createCreature(request));
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("{creatureID}")
    public ResponseEntity<Void> deleteCreature(@PathVariable String creatureID)
    {
        deleteCreatureService.deleteCreature(creatureID);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @PutMapping("{creatureID}")
    public ResponseEntity<Void> updateCreature(@PathVariable("creatureID") String creatureID, @RequestBody UpdateCreatureRequestDTO request)
    {
        request.setCreatureID(creatureID);
        updateCreatureService.updateCreature(request);
        return ResponseEntity.noContent().build();
    }

    @MessageMapping("/create-creature")
    @SendTo("/creature/notification")
    public CreatureNotificationDTO createCreatureNotification(){
        return CreatureNotificationDTO.builder().content("New creature has been added, check out the lists to see more details").build();
    }

    @MessageMapping("/update-creature")
    @SendTo("/creature/notification")
    public CreatureNotificationDTO updateCreatureNotification(){
        return CreatureNotificationDTO.builder().content("A creature has been updated, check out the lists to see more details").build();
    }

    @MessageMapping("/delete-creature")
    @SendTo("/creature/notification")
    public CreatureNotificationDTO deleteCreatureNotification(){
        return CreatureNotificationDTO.builder().content("A creature has been deleted!").build();
    }
}
