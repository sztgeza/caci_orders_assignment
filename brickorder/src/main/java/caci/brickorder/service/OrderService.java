package caci.brickorder.service;

import java.util.Collection;

import caci.brickorder.domain.Order;

public interface OrderService {
	Order createOrder(Order order);
	
	Collection<Order> findAll();
	
	Order getOrder(Long id);
	
	Order updateOrFulfilOrder(Order order);
}
