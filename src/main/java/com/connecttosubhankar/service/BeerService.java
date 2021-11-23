package com.connecttosubhankar.service;

import com.comon.brewery.model.BeerDto;
import com.comon.brewery.model.BeerPagedList;
import com.comon.brewery.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDto findBeerById(UUID id,Boolean showInventory );

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID id, BeerDto beerDto);

    void deleteBeerById(UUID id);

    BeerPagedList listBeer(String beerName, BeerStyleEnum beerStyle, PageRequest of,Boolean showInventory);

    BeerDto findBeerByUPC(String upc);
}
