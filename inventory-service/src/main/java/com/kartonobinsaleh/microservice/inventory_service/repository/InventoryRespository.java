package com.kartonobinsaleh.microservice.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartonobinsaleh.microservice.inventory_service.model.Inventory;

public interface InventoryRespository extends JpaRepository<Inventory, Long> {
    boolean existsBySkuCodeAndQuantityIsGreaterThanEquals(String skuCode, Integer quantity);
}
