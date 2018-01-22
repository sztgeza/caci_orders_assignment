package caci.brickorder.integrationtest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import caci.brickorder.domain.Order;
import caci.brickorder.repository.OrderRepository;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;

public class BrickOrderStepDefs {
    @Autowired
    OrderRepository orderRepo;

    @Given("^the db is empty$")
    public void clearDb() throws Throwable {
        orderRepo.deleteAll();
    }

    @Given("^the following orders exist:$")
    public void createOrders(DataTable orders) throws Throwable {
        List<Order> orderList = orders.asList(Order.class);
        orderList.forEach(orderRepo::save);        
    }
	
}
