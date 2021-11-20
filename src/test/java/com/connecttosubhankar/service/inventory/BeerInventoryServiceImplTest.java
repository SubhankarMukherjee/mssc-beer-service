package com.connecttosubhankar.service.inventory;


import com.connecttosubhankar.bootstrap.BeerLoader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@Disabled
@SpringBootTest
class BeerInventoryServiceImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @Test
    void getOnHandInventory()
    {
        Integer inventory = beerInventoryService.getOnHandInventory(BeerLoader.BEER_1_UUID);
        System.out.println("inventory:"+inventory);

    }
}