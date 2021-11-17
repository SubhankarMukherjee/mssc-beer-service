package com.connecttosubhankar.mapper;


import com.connecttosubhankar.domain.Beer;
import com.connecttosubhankar.web.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerDto convertBeerToBeerDTOWithInventory(Beer beer);
    BeerDto convertBeerToBeerDTOWithoutInventory(Beer beer);

    Beer covertBeerDTOToBeer(BeerDto beerDTOV2);
}
