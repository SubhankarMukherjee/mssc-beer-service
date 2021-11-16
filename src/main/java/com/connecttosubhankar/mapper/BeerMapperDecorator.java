package com.connecttosubhankar.mapper;

import com.connecttosubhankar.domain.Beer;
import com.connecttosubhankar.service.inventory.BeerInventoryService;
import com.connecttosubhankar.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BeerMapperDecorator implements BeerMapper {

    @Autowired
    private BeerMapper mapper;

    @Autowired
    private BeerInventoryService inventoryService;


    @Override
    public BeerDto convertBeerToBeerDTO(Beer beer) {
        BeerDto beerDto = mapper.convertBeerToBeerDTO(beer);
        Integer sumOfQuantityInHand = inventoryService.getOnHandInventory(beer.getId());
        beerDto.setQuantityOnHand(sumOfQuantityInHand);
        return beerDto;
    }

    @Override
    public Beer covertBeerDTOToBeer(BeerDto beerDTO) {
        return mapper.covertBeerDTOToBeer(beerDTO);
    }
}
