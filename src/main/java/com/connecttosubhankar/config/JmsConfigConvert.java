package com.connecttosubhankar.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfigConvert {

    public static final String BFREWING_REQUEST_QUEUE= "brewing-request";
    public static final String INVENTORY_EVENT_QUEUE= "inventory_queue";
    public static final String VALIDATE_ORDER_QUEUE = "validate-order";
    public static final String VALIDATE_ORDER_RESPONSE_QUEUE="validate-result";
    public static final String ALLOCATE_ORDER_QUEUE="allocate-order";
    public static final String ALLOCATE_ORDER_RESPONSE_QUEUE="allocate-result";

    //public static final String MY_SEND_RCV_QUEUE="send-receive";
    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(objectMapper);
        return converter;
    }

}
