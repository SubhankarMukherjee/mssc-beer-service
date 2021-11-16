package com.connecttosubhankar.service;

import com.connecttosubhankar.web.model.BeerDto;
import com.connecttosubhankar.web.model.BeerPagedList;
import com.connecttosubhankar.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDto findBeerById(UUID id);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID id, BeerDto beerDto);

    void deleteBeerById(UUID id);

    BeerPagedList listBeer(String beerName, BeerStyleEnum beerStyle, PageRequest of);
}
