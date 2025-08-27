/*OrderController.java
Author: Tsholofelo Mabidikane (230018165)
Date: 07 August 2025
 */
package za.ac.cput.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.business.Order;
import za.ac.cput.service.business.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Order create(@RequestBody Order order) {
        return service.create(order);
    }

    @GetMapping("/read/{orderId}")
    public Order read(@PathVariable Integer orderId) {
        return service.read(orderId);
    }

    @PutMapping("/update")
    public Order update(@RequestBody Order order) {
        return service.update(order);
    }

    @DeleteMapping("/delete/{orderId}")
    public boolean delete(@PathVariable Integer orderId) {
        return service.deleteOrder(orderId);
    }

    @GetMapping("/getall")
    public List<Order> getAll() {
        return service.getAllOrders();
    }
}
