/*OrderFactoryTest.java
Order model class
Author: Tsholofelo Mabidikane (230018165)
Date: 17 May 2025
 */
package za.ac.cput.factory.business;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.business.Order;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.users.CustomerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderFactoryTest {

    private static final Customer validCustomer = CustomerFactory.createCustomer(
            "Tsholofelo",
            "Mabidikane",
            "0721234567",
            "tsholo@mabidikane.com",
            "183j#n2ne@1",
            null
    );

    @Test
    void testCreateOrder() {
        Order order = OrderFactory.createOrder(validCustomer, LocalDate.of(2025, 8, 28), 250.00);
        assertNotNull(order, "Order should be created with valid data");
        System.out.println("Created order: " + order);
    }

    @Test
    void testWithNoCustomer() {
        Order order = OrderFactory.createOrder(null, LocalDate.of(2025, 8, 28), 250.00);
        assertNull(order, "Order should not be created with null customer");
    }

    @Test
    void testWithNoDate() {
        Order order = OrderFactory.createOrder(validCustomer, null, 250.00);
        assertNull(order, "Order should not be created with null date");
    }

    @Test
    void testWithInvalidAmount() {
        Order order = OrderFactory.createOrder(validCustomer, LocalDate.of(2025, 8, 28), -50.0);
        assertNull(order, "Order should not be created with negative amount");
    }
}