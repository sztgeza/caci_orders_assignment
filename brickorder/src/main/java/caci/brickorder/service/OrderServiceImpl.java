package caci.brickorder.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import caci.brickorder.domain.Order;
import caci.brickorder.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	private OrderRepository orderRepository;
	private OrderUpdateValidator validator;
	private UpdateOrderFactory updateOrderFactory;
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, OrderUpdateValidator validator, UpdateOrderFactory updateOrderFactory) {
		this.orderRepository = orderRepository;   
		this.validator = validator;
		this.updateOrderFactory = updateOrderFactory;
	}

	@Override
	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Collection<Order> findAll() {
		return orderRepository.getOrders();
	}

	@Override
	public Order getOrder(Long id) {
		return orderRepository.getOrder(id);
	}
	
	@Override
	public Order updateOrFulfilOrder(Order order) {		
		Order persistedOrder = orderRepository.getOrder(order.getId());		
		validator.validate(order, persistedOrder);
		return orderRepository.save(updateOrderFactory.createUpdatedOrder(order, persistedOrder));
	}
}
