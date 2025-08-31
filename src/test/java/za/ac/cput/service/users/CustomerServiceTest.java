package za.ac.cput.service.users;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.generic.Address;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.generic.AddressFactory;
import za.ac.cput.factory.users.CustomerFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CustomerServiceTest {
    @Autowired
    private ICustomerService service;

    private static Address address = AddressFactory.createAddress(
            null,
            "10 Main Road",
            "Cape Town",
            "Western Cape",
            "8000",
            "South Africa"
    );

    private static Customer customer = CustomerFactory.createCustomer(
            "Agnes",
            "Mabusela",
            "0833838288",
            "agnes@gmail.com",
            "password2025",
             address
    );

    @Test
    void a_create() {
        Customer created = service.create(customer);
        assertNotNull(created);
        customer = created;
        System.out.println(created);
    }

    @Test
    void b_read() {
        Customer read = service.read(customer.getId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        Customer newCustomer = new Customer.Builder().copy(customer).setFirstName("Madikila").build();
        Customer updated = service.update(newCustomer);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void e_delete() {
        boolean deleted = service.delete(customer.getId());
        assertTrue(deleted);
        System.out.println("Deleted: "+ deleted);
    }

    @Test
    void d_getAll() {
        assertFalse(service.getAll().isEmpty());
        System.out.println(service.getAll());
    }

}