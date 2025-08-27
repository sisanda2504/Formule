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
import za.ac.cput.domain.business.Shipping;
import za.ac.cput.factory.business.ShippingFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class ShippingServiceTest {

    @Autowired
    private static IShippingService service;

    private static Shipping shipping = ShippingFactory.createShipping(
            1,
            105,
            "143 Sir Lowry Road",
            "Pending",
            LocalDate.of(2025, 10, 1),
            "346578964"
    );

    @Test
    void a_create() {
        Shipping created = service.create(shipping);
        assertNotNull(created);
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
                .setStatus("Shipped")
                .build();
        Shipping updated = service.update(updatedShipping);
        assertNotNull(updated);
        System.out.println("Updated: " + updated);
    }

    @Test
    void d_delete() {
        boolean deleted = service.deleteShipping(shipping.getId());
        assertTrue(deleted);
        System.out.println("Deleted Shipping with ID: " + shipping.getId());
    }

    @Test
    void e_getAll() {
        System.out.println("All Shippings: " + service.getAll());
    }
}