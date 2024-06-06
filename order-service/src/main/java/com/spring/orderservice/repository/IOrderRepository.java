package com.spring.orderservice.repository;

import com.spring.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order,Long> {

}
