/*ShippingFactoryTest.java
Shipping model class
Author: Tsholofelo Mabidikane (230018165)
Date: 17 May 2025
 */
package za.ac.cput.factory.business;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.business.Order;
import za.ac.cput.domain.business.Shipping;
import za.ac.cput.domain.business.Status;
import za.ac.cput.domain.generic.Address;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.generic.AddressFactory;
import za.ac.cput.factory.users.CustomerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShippingFactoryTest {

    private static Customer customer;
    private static Address address;
    private static Order order;
    private static Shipping shipping;

    @BeforeAll
    static void setup() {
        customer = CustomerFactory.createCustomer(
                "Jane", "Doe", "0123456789", "jane@example.com", "Password@123", null
        );

        address = AddressFactory.createAddress(
                customer,
                "10 Test Lane",
                "Cape Town",
                "Western Cape",
                "8000",
                "South Africa"
        );

        order = OrderFactory.createOrder(
                customer,
                LocalDate.of(2025, 9, 1),
                500.00
        );

        shipping = ShippingFactory.createShipping(
                order,
                "123 Main Street",
                Status.InTransit,
                LocalDate.of(2025, 9, 5),
                "TN47586"
        );
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void testCreateShippingSuccess() {
        assertNotNull(shipping, "Shipping should be created successfully");
        assertEquals(order, shipping.getOrder(), "Order should match");
        assertEquals("123 Main Street", shipping.getAddress(), "Address should match");
        assertEquals(Status.InTransit, shipping.getStatus(), "Status should match");
        assertEquals(LocalDate.of(2025, 9, 5), shipping.getEstimatedDeliveryDate(), "Estimated delivery date should match");
        assertEquals("TN47586", shipping.getTrackingNumber(), "Tracking number should match");
        System.out.println("Created shipping: " + shipping);
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void testCreateShippingWithNullOrder() {
        Shipping invalid = ShippingFactory.createShipping(
                null,
                "123 Main Street",
                Status.Delivered,
                LocalDate.of(2025, 9, 5),
                "TN12345"
        );
        assertNull(invalid, "Shipping creation should fail when order is null");
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void testCreateShippingWithEmptyAddress() {
        Shipping invalid = ShippingFactory.createShipping(
                order,
                "",
                Status.Delayed,
                LocalDate.of(2025, 9, 5),
                "TN54321"
        );
        assertNull(invalid, "Shipping creation should fail when address is empty");
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void testCreateShippingWithNullStatus() {
        Shipping invalid = ShippingFactory.createShipping(
                order,
                "456 Oak Street",
                null,
                LocalDate.of(2025, 9, 5),
                "TN99999"
        );
        assertNull(invalid, "Shipping creation should fail when status is null");
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    void testCreateShippingWithNullTrackingNumber() {
        Shipping invalid = ShippingFactory.createShipping(
                order,
                "789 Elm Street",
                Status.Returned,
                LocalDate.of(2025, 9, 5),
                null
        );
        assertNull(invalid, "Shipping creation should fail when tracking number is null");
    }
}