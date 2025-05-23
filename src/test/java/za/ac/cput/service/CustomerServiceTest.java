package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CustomerFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CustomerServiceTest {
    @Autowired
    private static ICustomerService service;
    private static Customer customer = CustomerFactory.createCustomer(
            "Agnes",
            "Mabusela",
            "0769272996",
            "agnes@gmail.com",
            "password2025",
            67
    );

    @Test
    void a_create() {
        Customer created = service.create(customer);
        assertNotNull(created);
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
    void e_deleteCustomer() {

    }

    @Test
    void d_getAll() {
        System.out.println(service.getAll());
    }
}