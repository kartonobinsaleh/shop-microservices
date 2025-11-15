package com.kartonobinsaleh.microservices.order_service.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kartonobinsaleh.microservices.order_service.client.InventoryClient;
import com.kartonobinsaleh.microservices.order_service.dto.OrderRequest;
import com.kartonobinsaleh.microservices.order_service.model.Order;
import com.kartonobinsaleh.microservices.order_service.repository.OrderRespository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRespository orderRespository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest) {
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if (isProductInStock) {
            Order order = new Order();

            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());

            orderRespository.save(order);
        } else {
            throw new RuntimeException("Product with SkuCode" + orderRequest.skuCode() + "is not in stock");
        }

    }
}
