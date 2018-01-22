package caci.brickorder.service;

import org.springframework.stereotype.Component;

import caci.brickorder.domain.Order;

@Component
public class UpdateOrderFactoryImpl implements UpdateOrderFactory {

	@Override
	public Order createUpdatedOrder(Order order, Order persistedOrder) {
		return new Order(persistedOrder.getId(), order.getBricks(), order.getStatus());
	}	
}
