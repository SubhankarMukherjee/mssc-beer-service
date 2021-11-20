package com.connecttosubhankar.service.brewery;

import com.connecttosubhankar.config.JmsConfigConvert;
import com.connecttosubhankar.domain.Beer;
import common.events.BrewBeerEvent;
import common.events.NewInventoryEvent;
import com.connecttosubhankar.repositories.BeerRepository;
import com.connecttosubhankar.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;
    @Transactional
    @JmsListener(destination = JmsConfigConvert.BFREWING_REQUEST_QUEUE)
    void onMessage(BrewBeerEvent brewBeerEvent)
    {
        BeerDto beerDto = brewBeerEvent.getBeerDto();
        // brew the beer
        //quantity to brew exist on beer not in beer DTO
        Beer beer = beerRepository.findById(beerDto.getId()).orElseThrow(null);
        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        log.debug("Brewing beer:"+ beer.getMinOnHand()+ "QOH"+ beerDto.getQuantityOnHand());
        NewInventoryEvent newInventoryEvent= new NewInventoryEvent(beerDto);
        jmsTemplate.convertAndSend(JmsConfigConvert.INVENTORY_EVENT_QUEUE, newInventoryEvent);

    }
}
