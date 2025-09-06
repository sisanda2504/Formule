/*ShippingServiceTest.java
Author: Tsholofelo Mabidikane (230018165)
Date: 05 August 2025
 */
package za.ac.cput.service.business;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.business.Order;
import za.ac.cput.domain.business.Shipping;
import za.ac.cput.domain.business.Status;
import za.ac.cput.domain.generic.Address;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.business.OrderFactory;
import za.ac.cput.factory.business.ShippingFactory;
import za.ac.cput.factory.generic.AddressFactory;
import za.ac.cput.factory.users.CustomerFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class ShippingServiceTest {

    @Autowired
    private IShippingService service;

    private static Customer customer = CustomerFactory.createCustomer(
            "John", "Smith", "0821234567", "john@example.com", "Passw0rd!"
    );

    private static Address address = AddressFactory.createAddress(
            customer, "143 Sir Lowry Road", "Cape Town", "Western Cape", "8001", "South Africa"
    );

    private static Order order = OrderFactory.createOrder(
            customer,
            LocalDate.of(2025, 9, 15),
            1000.00
    );

    private static Shipping shipping = ShippingFactory.createShipping(
            order,
            address.getStreet(),
            Status.Pending,
            LocalDate.of(2025, 10, 1),
            "346578964"
    );

    @Test
    void a_create() {
        Shipping created = service.create(shipping);
        assertNotNull(created);
        shipping = created;
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        Shipping read = service.read(shipping.getId());
        assertNotNull(read);
        System.out.println("Read: " + read);
    }

    @Test
    void c_update() {
        Shipping updatedShipping = new Shipping.Builder()
                .copy(shipping)
                .setStatus(Status.Shipped)
                .build();

        Shipping updated = service.update(updatedShipping);
        assertNotNull(updated);
        System.out.println("Updated: " + updated);
    }

    @Test
    void d_delete() {
        boolean deleted = service.delete(shipping.getId());
        assertTrue(deleted);
        System.out.println("Deleted Shipping with ID: " + shipping.getId());
    }

    @Test
    void e_getAll() {
        System.out.println("All Shippings: " + service.getAll());
    }
}