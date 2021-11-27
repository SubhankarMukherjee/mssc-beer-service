package com.connecttosubhankar.service.inventory;


import com.connecttosubhankar.service.inventory.model.BeerInventoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Profile("non-discovery")
@Slf4j
@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BeerInventoryServiceImpl implements BeerInventoryService{

    public static final String INVENTORY_PATH="/api/v1/beer/{beerId}/inventory";
    private final RestTemplate restTemplate;

    private  String BEER_INVENTORY_SERVICE_HOST;

    public BeerInventoryServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public void setBEER_INVENTORY_SERVICE_HOST(String BEER_INVENTORY_SERVICE_HOST)
    {
        this.BEER_INVENTORY_SERVICE_HOST=BEER_INVENTORY_SERVICE_HOST;
    }




    @Override
    public Integer getOnHandInventory(UUID beerId) {

        log.debug("Calling Inventory Service");
        ResponseEntity<List<BeerInventoryDTO>> beerInventiryDTOResponseEntity = restTemplate.exchange(BEER_INVENTORY_SERVICE_HOST + INVENTORY_PATH, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<BeerInventoryDTO>>() {
                }, (Object) beerId);

        // sum from inventory List

        Integer onHand= Objects.requireNonNull(beerInventiryDTOResponseEntity.getBody().stream()
                .mapToInt(BeerInventoryDTO::getQuantityOnHand))
                .sum();

        return onHand;
    }
}
