package com.connecttosubhankar.service;

import com.connecttosubhankar.config.JmsConfigConvert;
import com.connecttosubhankar.domain.Beer;
import com.connecttosubhankar.event.BeerEvent;
import com.connecttosubhankar.mapper.BeerMapper;
import com.connecttosubhankar.repositories.BeerRepository;
import com.connecttosubhankar.service.inventory.BeerInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate=5000)  // At every 5 seconds
    void checkForLowQuantity()
    {
        List<Beer> beers = beerRepository.findAll();
        beers.forEach(beer -> {
            Integer invenQOH = beerInventoryService.getOnHandInventory(beer.getId());
            log.debug("Inventory on hand:"+ invenQOH);
            log.debug("Min on hand is :"+ beer.getMinOnHand());
            if(beer.getMinOnHand()>=invenQOH)
            {
                log.debug("Brewing request sending... Need  for it");
                jmsTemplate.convertAndSend(JmsConfigConvert.BFREWING_REQUEST_QUEUE,new BeerEvent(beerMapper.convertBeerToBeerDTOWithInventory(beer)));
            }

        }
        );
    }
}
