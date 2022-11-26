package com.kasintu.controllers;

import com.kasintu.dtos.inventorydtos.GetAllInventoriesResponseDTO;
import com.kasintu.dtos.inventorydtos.InventoryDTO;
import com.kasintu.services.inventoryservices.GetAllInventoriesService;
import com.kasintu.services.inventoryservices.impl.GetInventoryByInventoryIDServiceImpl;
import com.kasintu.services.inventoryservices.impl.GetInventoryByUserIDServiceImpl;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(InventoryController.class)
class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetAllInventoriesService getAllInventoriesService;
    @MockBean
    private GetInventoryByInventoryIDServiceImpl getInventoryByInventoryIDService;
    @MockBean
    private GetInventoryByUserIDServiceImpl getInventoryByPlayerIDService;

    @Test
    void getAllInventory_Return200() throws Exception {
        GetAllInventoriesResponseDTO response = GetAllInventoriesResponseDTO.builder()
                .inventories(List.of(
                        InventoryDTO.builder()
                                .inventoryID("1")
                                .playerID("1")
                                .ownedCreatures(List.of())
                                .build(),
                        InventoryDTO.builder()
                                .inventoryID("2")
                                .playerID("2")
                                .ownedCreatures(List.of())
                                .build()
                ))
                .build();
        when(getAllInventoriesService.getAllInventories()).thenReturn(response);
        mockMvc.perform(get("/inventory"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                        """
                        {
                            "inventories":
                            [
                                {
                                    "inventoryID": "1",
                                    "playerID": "1",
                                    "ownedCreatures": []
                                },
                                {
                                    "inventoryID": "2",
                                    "playerID": "2",
                                    "ownedCreatures": []
                                }
                            ]
                        }
                        """
                ));
        verify(getAllInventoriesService).getAllInventories();
    }

    @Test
    void getInventoryByInventoryID_Return200() throws Exception {
        InventoryDTO response = InventoryDTO.builder()
                .inventoryID("1")
                .playerID("2")
                .ownedCreatures(List.of())
                .build();
        when(getInventoryByInventoryIDService.getInventory("1")).thenReturn(Optional.of(response));

        mockMvc.perform(get("/inventory/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                    {
                        "inventoryID": "1",
                        "playerID": "2",
                        "ownedCreatures": []
                    }
                """));
        verify(getInventoryByInventoryIDService).getInventory("1");
    }

    @Test
    void getInventoryByInventoryID_Return404() throws Exception {
        when(getInventoryByInventoryIDService.getInventory("1")).thenReturn(Optional.empty());

        mockMvc.perform(get("/inventory/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(getInventoryByInventoryIDService).getInventory("1");
    }

    @Test
    void getInventoryByUserID_Return200() throws Exception {
        InventoryDTO response = InventoryDTO.builder()
                .inventoryID("1")
                .playerID("2")
                .ownedCreatures(List.of())
                .build();
        when(getInventoryByPlayerIDService.getInventory("2")).thenReturn(Optional.of(response));

        mockMvc.perform(get("/inventory/player/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                    {
                        "inventoryID": "1",
                        "playerID": "2",
                        "ownedCreatures": []
                    }
                """));
        verify(getInventoryByPlayerIDService).getInventory("2");
    }

    @Test
    void getInventoryByUserID_Return404() throws Exception {
        when(getInventoryByPlayerIDService.getInventory("2")).thenReturn(Optional.empty());

        mockMvc.perform(get("/inventory/player/2"))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(getInventoryByPlayerIDService).getInventory("2");
    }
}