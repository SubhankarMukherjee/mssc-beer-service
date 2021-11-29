package com.connecttosubhankar.service.inventory;

import com.connecttosubhankar.service.inventory.model.BeerInventoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "inventory-failover-service")
public interface FeignInventoryFailBackService {

    @GetMapping("/inventory-failover")
    ResponseEntity<List<BeerInventoryDTO>> getlistBeersById();

}