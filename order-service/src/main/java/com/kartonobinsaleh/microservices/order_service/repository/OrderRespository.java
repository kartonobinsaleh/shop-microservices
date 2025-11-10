package com.kartonobinsaleh.microservices.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartonobinsaleh.microservices.order_service.model.Order;

public interface OrderRespository extends JpaRepository<Order, Long> {

}
