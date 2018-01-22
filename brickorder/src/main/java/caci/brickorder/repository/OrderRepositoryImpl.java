package caci.brickorder.repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import caci.brickorder.domain.Order;
import caci.brickorder.domain.Status;

@Repository
public class OrderRepositoryImpl implements OrderRepository {	
	private AtomicLong idCounter = new AtomicLong();
	private Map<Long, Order> orders = new ConcurrentHashMap<>();
	
	private Long createID() {
	    return idCounter.getAndIncrement();
	}
	
	public Order save(Order order) {
		Order toSave = order;
		if (order.getId() == null) { 
			toSave = new Order(createID(), order.getBricks(), Status.NEW);			
		}
		orders.put(toSave.getId(), toSave);
		return toSave;
	}
	
	public Order getOrder(Long id) {
		return orders.get(id);
	}
	
	public Collection<Order> getOrders() {		
		return orders.values();
	}
	
	public void deleteAll() {
		orders.clear();
		idCounter.set(0);
	}
}
