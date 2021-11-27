package com.connecttosubhankar.service.inventory;

import com.connecttosubhankar.service.inventory.model.BeerInventoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
@RequiredArgsConstructor
@Service
public class BeerInventoryServiceFeignImpl  implements  BeerInventoryService{

    private final FeignInventoryService feignInventoryService;
    @Override
    public Integer getOnHandInventory(UUID beerId) {

        ResponseEntity<List<BeerInventoryDTO>> listResponseEntity = feignInventoryService.getlistBeersById(beerId);


        Integer onHand= Objects.requireNonNull(listResponseEntity.getBody().stream()
                        .mapToInt(BeerInventoryDTO::getQuantityOnHand))
                .sum();

        return onHand;
    }
}
