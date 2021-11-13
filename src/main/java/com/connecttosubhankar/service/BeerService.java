package com.connecttosubhankar.service;

import com.connecttosubhankar.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto findBeerById(UUID id);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID id, BeerDto beerDto);

    void deleteBeerById(UUID id);
}
