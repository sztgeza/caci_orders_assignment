package caci.brickorder.service;

import caci.brickorder.domain.Order;

public interface OrderUpdateValidator {
	void validate(Order order, Order persistedOrder);
}
