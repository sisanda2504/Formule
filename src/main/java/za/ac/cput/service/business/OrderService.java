package za.ac.cput.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.business.Cart;
import za.ac.cput.domain.business.CartItems;
import za.ac.cput.domain.business.Order;
import za.ac.cput.repository.business.OrderRepository;

import java.time.LocalDate;
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
    public Order read(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public Order update(Order order) {
        return this.repository.save(order);
    }

    @Override
    public boolean delete(Long id) {
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

    @Override
    public List<Order> findOrdersByCustomerId(Long customerId) {
        return this.repository.findByCustomerId(customerId);
    }

    @Override
    @Transactional
    public Order checkout(Cart cart) {
        if (cart == null || cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        double totalAmount = cart.getCartItems()
                                 .stream()
                                 .mapToDouble(CartItems::getItemTotal)
                                 .sum();

        Order order = new Order.Builder()
                .setCustomer(cart.getCustomer())
                .setOrderDate(LocalDate.now())
                .setTotalAmount(totalAmount)
                .build();

        return repository.save(order);
    }
}