package com.connecttosubhankar.web.controller;

import com.connecttosubhankar.bootstrap.BeerLoader;
import com.connecttosubhankar.service.BeerService;
import com.connecttosubhankar.web.model.BeerDto;
import com.connecttosubhankar.web.model.BeerStyleEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeerService beerService;

    @Autowired
    ObjectMapper objectMapper;
    @Test
    void getBeerById() throws Exception {

        given(beerService.findBeerById(any())).willReturn(validBeer());
        mockMvc.perform(get("/api/v1/beer/"+ UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        given(beerService.saveNewBeer(any())).willReturn(validBeer());
        BeerDto beerDto=validBeer();
        String jsonString = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(post("/api/v1/beer").content(jsonString).accept(MediaType.APPLICATION_JSON)
                .header("Content-Type","application/json"))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeer() throws Exception{
        given(beerService.updateBeer(any(),any())).willReturn(validBeer());
        BeerDto beerDto=validBeer();
        String jsonString = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(put("/api/v1/beer/"+ UUID.randomUUID().toString()).content(jsonString).accept(MediaType.APPLICATION_JSON)
                .header("Content-Type","application/json"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteById() throws Exception {
        //given(beerService.deleteBeerById(any())).willCallRealMethod(beerService.deleteBeerById());
        mockMvc.perform(delete("/api/v1/beer/"+UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    public BeerDto validBeer()
    {
        return BeerDto.builder().beerStyle(BeerStyleEnum.IPA).upc(BeerLoader.BEER_1_UPC).price(new BigDecimal((56.23))).beerName("abc").build();
    }
}