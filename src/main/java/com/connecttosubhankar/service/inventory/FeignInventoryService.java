package com.connecttosubhankar.service.inventory;

import com.connecttosubhankar.service.inventory.model.BeerInventoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "inventory-service")
public interface FeignInventoryService {

    @GetMapping(BeerInventoryServiceImpl.INVENTORY_PATH)
    ResponseEntity<List<BeerInventoryDTO>> getlistBeersById(@PathVariable UUID beerId);

}