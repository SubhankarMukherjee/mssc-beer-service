package com.connecttosubhankar.service.order;

import com.comon.brewery.model.BeerOrderDto;
import com.connecttosubhankar.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
@Slf4j
public class BeerOrderValidatiorByUPC {
    private final BeerRepository beerRepository;
    public Boolean validate(BeerOrderDto beerOrderDto)
    {
        AtomicInteger beerNotFound= new AtomicInteger();
        beerOrderDto.getBeerOrderLines().forEach(orderline->{
        if(beerRepository.findAllByUpc(orderline.getUpc())==null)
            {
                beerNotFound.incrementAndGet();
            }
        }
        );
        return beerNotFound.get()==0;
    }
}
