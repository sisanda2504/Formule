package za.ac.cput.service.generic;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.generic.Address;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.generic.AddressFactory;
import za.ac.cput.factory.users.CustomerFactory;
import za.ac.cput.service.users.CustomerService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @Autowired
    private CustomerService customerService;

    private static Customer customer;
    private static Address address;

    @BeforeAll
    static void setup(@Autowired CustomerService customerService) {
        Customer tempCustomer = CustomerFactory.createCustomer(
                "John",
                "Doe",
                "0712345678",
                "john.doe@example.com",
                "Password123!"
        );
        customer = customerService.create(tempCustomer);
        assertNotNull(customer.getId(), "Customer should be persisted before tests");
    }

    @Test
    @Order(1)
    void a_createAddress() {
        address = AddressFactory.createAddress(
                customer,
                "123 Main St",
                "Cape Town",
                "Western Cape",
                "8000",
                "South Africa"
        );

        Address saved = addressService.create(address);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        address = saved;
        System.out.println("✅ Created address: " + saved);
    }

    @Test
    @Order(2)
    void b_readAddress() {
        Address found = addressService.read(address.getId());
        assertNotNull(found);
        assertEquals(address.getStreet(), found.getStreet());
        System.out.println("📦 Read address: " + found);
    }

    @Test
    @Order(3)
    void c_updateAddress() {
        Address updatedAddress = new Address.Builder()
                .copy(address)
                .setCity("Durban")
                .build();

        Address updated = addressService.update(updatedAddress);
        assertNotNull(updated);
        assertEquals("Durban", updated.getCity());
        System.out.println("🔁 Updated address: " + updated);
    }

    @Test
    @Order(4)
    void d_getAllAddresses() {
        List<Address> allAddresses = addressService.getAll();
        assertFalse(allAddresses.isEmpty());
        System.out.println("📃 All addresses: " + allAddresses);
    }

    @Test
    @Order(5)
    void e_findByCustomerId() {
        List<Address> addresses = addressService.findByCustomerId(customer.getId());
        assertFalse(addresses.isEmpty());
        assertTrue(addresses.stream().anyMatch(a -> a.getId().equals(address.getId())));
        System.out.println("📍 Addresses by customer ID: " + addresses);
    }

    @Test
    @Order(6)
    void f_deleteAddress() {
        boolean deleted = addressService.delete(address.getId());
        assertTrue(deleted);
        System.out.println("🗑️ Deleted address: " + address.getId());
    }
}
