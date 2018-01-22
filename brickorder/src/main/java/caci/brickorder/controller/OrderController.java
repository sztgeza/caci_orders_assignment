package caci.brickorder.controller;

import java.net.URI;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import caci.brickorder.domain.Order;
import caci.brickorder.service.OrderService;

@RestController
public class OrderController {
	
	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@RequestMapping(value="/orders", method=RequestMethod.POST)
	public ResponseEntity<?> createOrder(@Valid @RequestBody Order order) {
		order = orderService.createOrder(order);
		HttpHeaders responseHeaders = new HttpHeaders();
		URI orderUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();
		responseHeaders.setLocation(orderUri);		
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value="/orders", method=RequestMethod.PUT)
	public ResponseEntity<?> updateOrder(@Valid @RequestBody Order order) {
		order = orderService.updateOrFulfilOrder(order);//orderService.updateOrder(order);
		HttpHeaders responseHeaders = new HttpHeaders();
		URI orderUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();
		responseHeaders.setLocation(orderUri);		
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value="/orders", method=RequestMethod.GET)
	public ResponseEntity<Collection<Order>> getAllOrders() {
		Collection<Order> allOrders = orderService.findAll();
		return new ResponseEntity<>(allOrders, HttpStatus.OK);
	}
	
	@RequestMapping(value="/orders/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> getOrder(@PathVariable Long id) {
		Order order = orderService.getOrder(id);
		return new ResponseEntity<> (order, HttpStatus.OK);
	}
}
