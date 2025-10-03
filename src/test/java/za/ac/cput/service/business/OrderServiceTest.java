/*OrderServiceTest.java
Author: Tsholofelo Mabidikane (230018165)
Date: 07 August 2025
 */
package za.ac.cput.service.business;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.business.Order;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.business.OrderFactory;
import za.ac.cput.factory.users.CustomerFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderServiceTest {

    @Autowired
    private IOrderService service;

    private static Customer customer = CustomerFactory.createCustomer(
            "Palesa",
            "Mabidikane",
            "0712345678",
            "palesam@icloud.com",
            "P@mabidikane"
    );

    private static Order order;

    @Test
    void a_create() {
        Order newOrder = OrderFactory.createOrder(
                customer,
                LocalDate.of(2025, 9, 15),
                100.00
        );
        assertNotNull(newOrder);

        Order created = service.create(newOrder);
        assertNotNull(created);
        assertNotNull(created.getId());
        order = created;  // Save created order with ID for subsequent tests
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        assertNotNull(order);
        Order read = service.read(order.getId());
        assertNotNull(read);
        System.out.println("Read: " + read);
    }

    @Test
    void c_update() {
        assertNotNull(order);
        Order updatedOrder = new Order.Builder()
                .copy(order)
                .setTotalAmount(150.00)
                .build();
        Order updated = service.update(updatedOrder);
        assertNotNull(updated);
        assertEquals(150.00, updated.getTotalAmount());
        order = updated;  // Update reference
        System.out.println("Updated: " + updated);
    }

    @Test
    void d_delete() {
        assertNotNull(order);
        boolean deleted = service.delete(order.getId());
        assertTrue(deleted);
        System.out.println("Deleted Order with ID: " + order.getId());
    }

    @Test
    void e_getAll() {
        var allOrders = service.getAllOrders();
        assertNotNull(allOrders);
        assertFalse(allOrders.isEmpty(), "Orders list should not be empty");
        System.out.println("All Orders: " + allOrders);
    }
}
