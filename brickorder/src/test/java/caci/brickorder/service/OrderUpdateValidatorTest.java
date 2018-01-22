package caci.brickorder.service;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import caci.brickorder.domain.Order;
import caci.brickorder.domain.Status;
import caci.brickorder.exception.AlreadyShippedOrderException;
import caci.brickorder.exception.InvalidOrderReferenceException;

@RunWith(MockitoJUnitRunner.class)
public class OrderUpdateValidatorTest {

	private OrderUpdateValidator validator = new OrderUpdateValidatorImpl();
	
	@Mock
	private Order persistedOrder;
	@Mock
	private Order order;
	
	@Before
	public void initialize() {		
	}
	
	@Test(expected = InvalidOrderReferenceException.class)
	public void persistedOrderIsNull() {
		validator.validate(order, null);
	}
	
	@Test(expected = AlreadyShippedOrderException.class)
	public void persistedOrderIsAlreadyShipped() {
		when(persistedOrder.getStatus()).thenReturn(Status.SHIPPED);
		validator.validate(order, persistedOrder);
	}	
}
