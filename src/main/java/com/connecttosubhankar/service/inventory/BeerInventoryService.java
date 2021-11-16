package com.connecttosubhankar.service.inventory;

import com.sun.xml.internal.fastinfoset.algorithm.UUIDEncodingAlgorithm;

import java.util.UUID;

public interface BeerInventoryService {

    Integer getOnHandInventory(UUID beerId);
}
