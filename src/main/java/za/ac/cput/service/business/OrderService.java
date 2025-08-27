/*
OrderService.java
Order model Service class
Author: Tsholofelo Mabidikane (230018165)
Date: 07 August 2025
 */
package za.ac.cput.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.business.Order;
import za.ac.cput.repository.business.OrderRepository;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order create(Order order) {
        return this.repository.save(order);
    }

    @Override
    public Order read(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public Order update(Order order) {
        return this.repository.save(order);
    }

    @Override
    public boolean deleteOrder(Integer id) {
        if (this.repository.existsById(id)) {
        this.repository.deleteById(id);
        return true;
    }
        return false;
    }

    @Override
    public List<Order> getAllOrders() {
        return this.repository.findAll();
    }
}
