package com.connecttosubhankar.mapper;


import com.connecttosubhankar.domain.Beer;
import com.connecttosubhankar.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto convertBeerToBeerDTO(Beer beer);

    Beer covertBeerDTOToBeer(BeerDto beerDTOV2);
}
