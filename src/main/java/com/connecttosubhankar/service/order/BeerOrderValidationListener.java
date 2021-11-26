package com.connecttosubhankar.service.order;

import com.comon.brewery.model.BeerOrderDto;
import com.comon.brewery.model.event.ValidateOrderRequest;
import com.comon.brewery.model.event.ValidateOrderResult;
import com.connecttosubhankar.config.JmsConfigConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private  final BeerOrderValidatiorByUPC beerOrderValidatiorByUPC;
    private final JmsTemplate jmsTemplate;
   @JmsListener(destination=JmsConfigConvert.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateOrderRequest validateOrderRequest) throws JMSException
    {
      BeerOrderDto beerOrderDto= validateOrderRequest.getBeerOrderDto();
        Boolean isValid = beerOrderValidatiorByUPC.validate(beerOrderDto);
       jmsTemplate.convertAndSend(JmsConfigConvert.VALIDATE_ORDER_RESPONSE_QUEUE,
               ValidateOrderResult.builder().isValid(isValid).orderId(beerOrderDto.getId()).build());
        System.out.println("Validation Response Sent for ID: "+ beerOrderDto.getId().toString());
    }
}
