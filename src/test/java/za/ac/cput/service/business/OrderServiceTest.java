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

    private static Order order = OrderFactory.createOrder(
            customer,
            LocalDate.of(2025, 9, 15),
            100.00
    );

    @Test
    void a_create() {
        Order created = service.create(order);
        assertNotNull(created);
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        Order read = service.read(order.getId());
        assertNotNull(read);
        System.out.println("Read: " + read);
    }

    @Test
    void c_update() {
        Order updatedOrder = new Order.Builder()
                .copy(order)
                .setTotalAmount(150.00)
                .build();
        Order updated = service.update(updatedOrder);
        assertNotNull(updated);
        System.out.println("Updated: " + updated);
    }

    @Test
    void d_delete() {
        boolean deleted = service.delete(order.getId());
        assertTrue(deleted);
        System.out.println("Deleted Order with ID: " + order.getId());
    }

    @Test
    void e_getAll() {
        System.out.println("All Orders: " + service.getAllOrders());
    }
}