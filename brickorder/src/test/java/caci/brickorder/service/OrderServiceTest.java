package caci.brickorder.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import caci.brickorder.domain.Order;
import caci.brickorder.exception.AlreadyShippedOrderException;
import caci.brickorder.exception.InvalidOrderReferenceException;
import caci.brickorder.repository.OrderRepository;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
	@Mock
	private OrderUpdateValidator validator;
	@Mock
	private OrderRepository repository;
	@Mock
	private UpdateOrderFactory updateOrderFactory;
	
	@InjectMocks
	private OrderServiceImpl service;
	
	@Mock
	private Order order;
	@Mock
	private Order persistedOrder;
	@Mock
	private Collection<Order> persistedOrders;
	@Mock
	private Order orderToUpdate;
	@Mock
	private Order updatedOrder;
	
	
	@Before
	public void initialize() {
	}
	
	@Test
	public void getOrderTest() {
		when(repository.getOrder(1L)).thenReturn(persistedOrder);
		Order actualOrder = service.getOrder(1L);
		verify(repository, times(1)).getOrder(1L);
		assertThat(actualOrder, equalTo(persistedOrder));		
	}

	@Test(expected = InvalidOrderReferenceException.class)	
	public void getOrderWhenThrowsExceptionTest() {
		when(repository.getOrder(1L)).thenThrow(new InvalidOrderReferenceException());
		Order actualOrder = service.getOrder(1L);
	}

	@Test
	public void createOrderTest() {
		when(repository.save(order)).thenReturn(persistedOrder);
		Order actualOrder = service.createOrder(order);
		verify(repository, times(1)).save(order);
		assertThat(actualOrder, equalTo(persistedOrder));		
	}
	
	@Test
	public void findAllTest() {
		when(repository.getOrders()).thenReturn(persistedOrders);
		Collection<Order> actualOrders = service.findAll();
		verify(repository, times(1)).getOrders();
		assertThat(actualOrders, equalTo(persistedOrders));		
	}
	
	@Test
	public void updateOrFulfilTest() {
		when(repository.getOrder(order.getId())).thenReturn(persistedOrder);
		when(updateOrderFactory.createUpdatedOrder(order, persistedOrder)).thenReturn(orderToUpdate);
		when(repository.save(orderToUpdate)).thenReturn(updatedOrder);
		Order actualOrder = service.updateOrFulfilOrder(order);
		
		verify(validator, times(1)).validate(order, persistedOrder);
		verify(updateOrderFactory, times(1)).createUpdatedOrder(order, persistedOrder);
		verify(repository, times(1)).save(orderToUpdate);
		assertThat(actualOrder.getId(), equalTo(order.getId()));
		assertThat(actualOrder.getBricks(), equalTo(order.getBricks()));
		assertThat(actualOrder.getStatus(), equalTo(order.getStatus()));
	}
	
	@Test(expected = InvalidOrderReferenceException.class)
	public void updateOrFulfilThrowsExceptionTest() {
		when(repository.getOrder(order.getId())).thenReturn(persistedOrder);
		when(updateOrderFactory.createUpdatedOrder(order, persistedOrder)).thenReturn(orderToUpdate);
		Mockito.doThrow(new InvalidOrderReferenceException()).when(validator).validate(order, persistedOrder);
		when(repository.save(orderToUpdate)).thenReturn(updatedOrder);
		Order actualOrder = service.updateOrFulfilOrder(order);
		//exception thrown
	}
	
	@Test(expected = AlreadyShippedOrderException.class)
	public void updateOrFulfilThrowsAlreadyShippedExceptionTest() {
		when(repository.getOrder(order.getId())).thenReturn(persistedOrder);
		when(updateOrderFactory.createUpdatedOrder(order, persistedOrder)).thenReturn(orderToUpdate);
		Mockito.doThrow(new AlreadyShippedOrderException()).when(validator).validate(order, persistedOrder);
		when(repository.save(orderToUpdate)).thenReturn(updatedOrder);
		Order actualOrder = service.updateOrFulfilOrder(order);
		//exception thrown
	}
}
