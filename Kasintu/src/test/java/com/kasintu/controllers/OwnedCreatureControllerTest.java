package com.kasintu.controllers;

import com.kasintu.dtos.ownedcreatureddtos.GetAllOwnedCreaturesRequestDTO;
import com.kasintu.dtos.ownedcreatureddtos.GetAllOwnedCreaturesResponseDTO;
import com.kasintu.dtos.ownedcreatureddtos.OwnedCreatureDTO;
import com.kasintu.dtos.ownedcreatureddtos.UpdateOwnedCreatureRequestDTO;
import com.kasintu.services.ownedcreatureservices.DeleteOwnedCreatureService;
import com.kasintu.services.ownedcreatureservices.GetAllOwnedCreaturesService;
import com.kasintu.services.ownedcreatureservices.GetOwnedCreatureService;
import com.kasintu.services.ownedcreatureservices.UpdateOwnedCreatureService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OwnedCreatureController.class)
class OwnedCreatureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetAllOwnedCreaturesService getAllOwnedCreaturesService;
    @MockBean
    private GetOwnedCreatureService getOwnedCreatureService;
    @MockBean
    private UpdateOwnedCreatureService updateOwnedCreatureService;
    @MockBean
    private DeleteOwnedCreatureService deleteOwnedCreatureService;

    @Test
    void getAllOwnedCreaturesResponseDTOResponseEntity_Return200() throws Exception {
        GetAllOwnedCreaturesResponseDTO response = GetAllOwnedCreaturesResponseDTO.builder()
                .ownedCreatures(
                        List.of(
                                OwnedCreatureDTO.builder()
                                        .inventoryID("1")
                                        .ownedCreatureID("1")
                                        .creature(null)
                                        .build(),
                                OwnedCreatureDTO.builder()
                                        .inventoryID("1")
                                        .ownedCreatureID("2")
                                        .creature(null)
                                        .build()
                        )
                )
                .build();
        GetAllOwnedCreaturesRequestDTO request = GetAllOwnedCreaturesRequestDTO.builder()
                .inventoryID("1")
                .build();
        when(getAllOwnedCreaturesService.getAllOwnedCreatures(request)).thenReturn(response);

        mockMvc.perform(get("/ownedCreature").param("inventoryID", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                        """
                                {
                                    "ownedCreatures":
                                    [
                                        {
                                            "inventoryID": "1",
                                            "ownedCreatureID": "1",
                                            "creature": null
                                        },
                                        {
                                            "inventoryID": "1",
                                            "ownedCreatureID": "2",
                                            "creature": null
                                        }
                                    ]
                                }
                                """
                ));
        verify(getAllOwnedCreaturesService).getAllOwnedCreatures(request);
    }

    @Test
    void getOwnedCreature_Return200() throws Exception {
        OwnedCreatureDTO ownedCreatureDTO = OwnedCreatureDTO.builder()
                .inventoryID("1")
                .ownedCreatureID("2")
                .creature(null)
                .build();
        when(getOwnedCreatureService.getOwnedCreature("2")).thenReturn(Optional.of(ownedCreatureDTO));

        mockMvc.perform(get("/ownedCreature/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                    {
                        "inventoryID": "1",
                        "ownedCreatureID": "2",
                        "creature": null
                    }
                """));
        verify(getOwnedCreatureService).getOwnedCreature("2");
    }

    @Test
    void getOwnedCreature_Return404() throws Exception {
        when(getOwnedCreatureService.getOwnedCreature("2")).thenReturn(Optional.empty());

        mockMvc.perform(get("/ownedCreature/2"))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(getOwnedCreatureService).getOwnedCreature("2");
    }

    @Test
    void DeleteOwnedCreature_Return204() throws Exception {
        mockMvc.perform(delete("/ownedCreature/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(deleteOwnedCreatureService).deleteOwnedCreature("1");
    }

    @Test
    void updateOwnedCreature_Return204() throws Exception {
        mockMvc.perform(put("/ownedCreature/1")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "ownedCreatureID": "1",
                            "inventoryID": "2"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isNoContent());

        UpdateOwnedCreatureRequestDTO expectedRequest = UpdateOwnedCreatureRequestDTO.builder()
                .ownedCreatureID("1")
                .inventoryID("2")
                .build();
        verify(updateOwnedCreatureService).updateOwnedCreature(expectedRequest);
    }
}