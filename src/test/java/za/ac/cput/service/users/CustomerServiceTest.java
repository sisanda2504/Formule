package za.ac.cput.service.users;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.generic.Address;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.generic.AddressFactory;
import za.ac.cput.factory.users.CustomerFactory;
import za.ac.cput.service.generic.AddressService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceTest {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private AddressService addressService;

    private static Customer customer;
    private static Address address;

    @Test
    @Order(1)
    void a_createCustomer() {
        customer = CustomerFactory.createCustomer(
                "Agnes",
                "Mabusela",
                "0833838288",
                "agnes@gmail.com",
                "password2025"
        );

        Customer created = customerService.create(customer);
        assertNotNull(created);
        customer = created;
        System.out.println("Created customer without address: " + created);
    }

    @Test
    @Order(2)
    void b_createAndLinkAddressToCustomer() {
        address = AddressFactory.createAddress(
                customer,
                "10 Main Road",
                "Cape Town",
                "Western Cape",
                "8000",
                "South Africa"
        );

        Address savedAddress = addressService.create(address);
        assertNotNull(savedAddress);
        address = savedAddress;
        System.out.println("Created address linked to customer: " + savedAddress);

        var addresses = addressService.findByCustomerId(customer.getId());
        assertFalse(addresses.isEmpty(), "Customer should have at least one address");
        assertTrue(addresses.stream().anyMatch(a -> a.getId().equals(savedAddress.getId())),
                "Saved address should be linked to customer");
    }


    @Test
    @Order(3)
    void c_readCustomer() {
        Customer read = customerService.read(customer.getId());
        assertNotNull(read);
        System.out.println("Read customer: " + read);
    }

    @Test
    @Order(4)
    void d_updateCustomer() {
        Customer updated = new Customer.Builder()
                .copy(customer)
                .setFirstName("Madikila")
                .build();

        Customer result = customerService.update(updated);
        assertNotNull(result);
        assertEquals("Madikila", result.getFirstName());
        System.out.println("Updated customer first name: " + result);
    }

    @Test
    @Order(5)
    void e_getAllCustomers() {
        assertFalse(customerService.getAll().isEmpty());
        System.out.println("All customers: " + customerService.getAll());
    }

    @Test
    @Order(6)
    void f_deleteCustomer() {
        boolean deleted = customerService.delete(customer.getId());
        assertTrue(deleted);
        System.out.println("Deleted customer: " + deleted);
    }
}
