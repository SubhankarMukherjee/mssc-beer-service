package com.connecttosubhankar.repositories;

import com.connecttosubhankar.domain.Beer;
import com.connecttosubhankar.web.model.BeerDto;
import com.connecttosubhankar.web.model.BeerStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

    Page<Beer> findAllByBeerName(String beerName, PageRequest pageRequest);

    Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle,PageRequest pageRequest);

    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest);

    Beer findAllByUpc(String upc);
}
