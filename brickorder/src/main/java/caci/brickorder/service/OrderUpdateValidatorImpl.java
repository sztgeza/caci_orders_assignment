package caci.brickorder.service;

import org.springframework.stereotype.Component;

import caci.brickorder.domain.Order;
import caci.brickorder.domain.Status;
import caci.brickorder.exception.AlreadyShippedOrderException;
import caci.brickorder.exception.InvalidOrderReferenceException;

@Component
public class OrderUpdateValidatorImpl implements OrderUpdateValidator {
	public void validate(Order order, Order persistedOrder) {
		if (persistedOrder == null) {
			throw new InvalidOrderReferenceException();
		}
		if (!(persistedOrder.getStatus() == Status.NEW 
				&& (order.getStatus() == Status.SHIPPED || persistedOrder.getBricks() != order.getBricks()))) {
			throw new AlreadyShippedOrderException();
		}
	}
}
