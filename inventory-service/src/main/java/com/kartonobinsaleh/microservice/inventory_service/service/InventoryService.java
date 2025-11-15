package com.kartonobinsaleh.microservice.inventory_service.service;

import org.springframework.stereotype.Service;

import com.kartonobinsaleh.microservice.inventory_service.repository.InventoryRespository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRespository inventoryRespository;

    public boolean isInStock(String skuCode, Integer quantity) {
        return inventoryRespository.existsBySkuCodeAndQuantityIsGreaterThanEquals(skuCode, quantity);
    }

}
