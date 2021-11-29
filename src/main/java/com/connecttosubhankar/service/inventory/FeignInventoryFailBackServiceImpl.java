package com.connecttosubhankar.service.inventory;

import com.connecttosubhankar.service.inventory.model.BeerInventoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class FeignInventoryFailBackServiceImpl implements FeignInventoryService{
    private final FeignInventoryFailBackService FeignInventoryFailBackService;

    @Override
    public ResponseEntity<List<BeerInventoryDTO>> getlistBeersById(UUID beerId) {
        return FeignInventoryFailBackService.getlistBeersById();
    }
}
