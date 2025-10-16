package za.ac.cput.factory.business;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.business.Order;
import za.ac.cput.domain.business.OrderItem;
import za.ac.cput.domain.business.Product;
import za.ac.cput.domain.users.Customer;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemFactoryTest {

    @Test
    void testCreateOrderItem() {
        Customer customer = new Customer.Builder()
                .setId(1L)
                .setFirstName("John")
                .setLastName("Doe")
                .build();

        Order order = new Order.Builder()
                .setCustomer(customer)
                .setOrderDate(LocalDate.now())
                .setTotalAmount(100.0)
                .build();

        Product product = new Product.Builder()
                .setId(1L)
                .setName("Test Product")
                .setPrice(50.0)
                .build();

        int quantity = 2;
        double itemTotal = product.getPrice() * quantity;

        OrderItem orderItem = OrderItemFactory.createOrderItem(order, product, quantity, itemTotal);

        assertNotNull(orderItem);
        assertEquals(order, orderItem.getOrder());
        assertEquals(product, orderItem.getProduct());
        assertEquals(quantity, orderItem.getQuantity());
        assertEquals(itemTotal, orderItem.getItemTotal());
    }

    @Test
    void testInvalidOrderItem() {
        OrderItem invalidItem = OrderItemFactory.createOrderItem(null, null, 0, -10.0);
        assertNull(invalidItem);
    }
}