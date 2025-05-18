package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Shipping;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ShippingFactoryTest {

    private static Shipping shipping = ShippingFactory.createShipping(1234567, 5678948, "123 Main Street", "Pending", LocalDateTime.of(2025, 8, 25, 14, 30), "TN47586");

    private static Shipping invalidShippingId = ShippingFactory.createShipping(-1, 5896732, "45 Lor Street", "Pending", LocalDateTime.of(2025, 8, 25, 14, 30), "47586");

    private static Shipping invalidOrderId = ShippingFactory.createShipping(4536896, -1, "22 Tambotie Street", "Pending", LocalDateTime.of(2025, 8, 25, 14, 30), "47586");

    private static Shipping invalidShippingAddress = ShippingFactory.createShipping(8987654, 6578930, "", "Pending", LocalDateTime.of(2025, 8, 25, 14, 30), "47586");

    private static Shipping invalidShippingStatus = ShippingFactory.createShipping(7894565, 7564875, "123 Main St", "", LocalDateTime.of(2025, 8, 25, 14, 30), "47586");

    @Test
    @Order(1)
    public void testCreateShippingSuccess() {
        System.out.println("Test: testCreateShippingSuccess");
        assertNotNull(shipping, "Shipping should be created successfully with valid data");
        System.out.println("Created shipping: " + shipping);
    }

    @Test
    @Order(2)
    public void testCreateShippingWithInvalidShippingId() {
        System.out.println("Test: testCreateShippingWithInvalidShippingId");
        assertNull(invalidShippingId, "Shipping with invalid shipping ID should not be created");
        System.out.println("Failed shipping creation with invalid shipping ID: " + invalidShippingId);
    }

    @Test
    @Order(3)
    public void testCreateShippingWithInvalidOrderId() {
        System.out.println("Test: testCreateShippingWithInvalidOrderId");
        assertNull(invalidOrderId, "Shipping with invalid order ID should not be created");
        System.out.println("Failed shipping creation with invalid order ID: " + invalidOrderId);
    }

    @Test
    @Order(4)
    public void testCreateShippingWithInvalidShippingAddress() {
        System.out.println("Test: testCreateShippingWithInvalidShippingAddress");
        assertNull(invalidShippingAddress, "Shipping with invalid shipping address should not be created");
        System.out.println("Failed shipping creation with invalid shipping address: " + invalidShippingAddress);
    }

    @Test
    @Order(5)
    public void testCreateShippingWithInvalidShippingStatus() {
        System.out.println("Test: testCreateShippingWithInvalidShippingStatus");
        assertNull(invalidShippingStatus, "Shipping with invalid shipping status should not be created");
        System.out.println("Failed shipping creation with invalid shipping status: " + invalidShippingStatus);
    }
}


