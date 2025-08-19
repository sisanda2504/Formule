package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Address;
import za.ac.cput.factory.AddressFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class AddressServiceTest {

    @Autowired
    private static IAddressService service;

    private static Address address = AddressFactory.createAddress(
            1,
            1001,
            "123 Main Street",
            "Cape Town",
            "Western Cape",
            "8000",
            "South Africa"
    );

    @Test
    void a_create() {
        Address created = service.create(address);
        assertNotNull(created);
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        Address read = service.read(address.getId());
        assertNotNull(read);
        System.out.println("Read: " + read);
    }

    @Test
    void c_update() {
        Address newAddress = new Address.Builder()
                .copy(address)
                .setStreet("456 Long Street")
                .build();
        Address updated = service.update(newAddress);
        assertNotNull(updated);
        System.out.println("Updated: " + updated);
    }

    @Test
    void d_getAll() {
        System.out.println("All addresses: " + service.getAll());
    }

    @Test
    void e_delete() {
        boolean success = service.delete(address.getId());
        assertTrue(success);
        System.out.println("Deleted: " + success);
    }
}
