package com.connecttosubhankar.service.order;

import com.comon.brewery.model.events.BeerOrderDto;
import com.comon.brewery.model.events.ValidateOrderRequest;
import com.comon.brewery.model.events.ValidateOrderResult;
import com.connecttosubhankar.config.JmsConfigConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private BeerOrderValidatiorByUPC beerOrderValidatiorByUPC;
    private final JmsTemplate jmsTemplate;
    @JmsListener(destination=JmsConfigConvert.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateOrderRequest validateOrderRequest)
    {
        BeerOrderDto beerOrderDto = validateOrderRequest.getBeerOrderDto();
        Boolean isValid = beerOrderValidatiorByUPC.validate(beerOrderDto);
       jmsTemplate.convertAndSend(JmsConfigConvert.VALIDATE_ORDER_RESPONSE_QUEUE,
               ValidateOrderResult.builder().isValid(isValid).orderId(beerOrderDto.getId()).build());
    }
}
