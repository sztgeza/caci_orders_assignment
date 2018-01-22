package caci.brickorder.repository;

import java.util.Collection;

import caci.brickorder.domain.Order;

public interface OrderRepository {
	Order save(Order order);
	Order getOrder(Long id);
	Collection<Order> getOrders();
	void deleteAll();
}
