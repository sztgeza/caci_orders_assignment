package caci.brickorder.service;

import caci.brickorder.domain.Order;

public interface UpdateOrderFactory {
	Order createUpdatedOrder(Order order, Order persistedOrder);
}
