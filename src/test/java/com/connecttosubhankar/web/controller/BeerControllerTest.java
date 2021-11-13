package com.connecttosubhankar.web.controller;

import com.connecttosubhankar.web.model.BeerDto;
import com.connecttosubhankar.web.model.BeerStyleEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get("/api/v1/beer/"+ UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto=BeerDto.builder().beerStyle(BeerStyleEnum.IPA).upc(6000l).price(new BigDecimal((56.23))).beerName("abc").build();
        String jsonString = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(post("/api/v1/beer").content(jsonString).accept(MediaType.APPLICATION_JSON)
                .header("Content-Type","application/json"))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeer() throws Exception{
        BeerDto beerDto=BeerDto.builder().beerStyle(BeerStyleEnum.IPA).upc(6000l).price(new BigDecimal((56.23))).beerName("abc").build();
        String jsonString = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(put("/api/v1/beer/"+ UUID.randomUUID().toString()).content(jsonString).accept(MediaType.APPLICATION_JSON)
                .header("Content-Type","application/json"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/v1/beer/"+UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}