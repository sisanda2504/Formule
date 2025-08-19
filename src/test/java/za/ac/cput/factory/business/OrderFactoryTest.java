/*OrderFactoryTest.java
Order model class
Author: Tsholofelo Mabidikane (230018165)
Date: 17 May 2025
 */
package za.ac.cput.factory.business;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.business.Order;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderFactoryTest {

    private static Order order = OrderFactory.createOrder(1234567, 5678948, LocalDateTime.of(2025, 8, 25, 14,30),  150.00);

    private static Order invalidOrderId = OrderFactory.createOrder(-1, 5896732, LocalDateTime.of(2025, 8, 25, 14,30), 300.00);

    private static Order invalidCustomerId = OrderFactory.createOrder(1234567, -1, LocalDateTime.of(2025, 8, 25, 14,30), 1500.00);

    @Test
    @Order(1)
    public void testCreateOrderSuccess() {
        System.out.println("Test: testCreateOrderSuccess");
        assertNotNull(order, "Order should be created successfully with valid data");
        System.out.println("Created order: " + order);
    }

    @Test
    @Order(2)
    public void testCreateOrderWithInvalidOrderId() {
        System.out.println("Test: testCreateOrderWithInvalidOrderId");
        assertNull(invalidOrderId, "Order with invalid order ID should not be created");
        System.out.println("Failed order creation with invalid order ID: " + invalidOrderId);
    }

    @Test
    @Order(3)
    public void testCreateOrderWithInvalidCustomerId() {
        System.out.println("Test: testCreateOrderWithInvalidCustomerId");
        assertNull(invalidCustomerId, "Order with invalid customer ID should not be created");
        System.out.println("Failed order creation with invalid customer ID: " + invalidCustomerId);
    }

}