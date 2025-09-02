package za.ac.cput.controller.users;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.generic.Address;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.generic.AddressFactory;
import za.ac.cput.factory.users.CustomerFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class CustomerControllerTest {

    private static Customer customer;
    private static Address address;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/formule/customer";

    @BeforeAll
    static void setUp() {
        address = AddressFactory.createAddress(
                null,
                "45 Example Street",
                "Cape Town",
                "Western Cape",
                "8001",
                "South Africa"
        );

        customer = CustomerFactory.createCustomer(
                "Agnes",
                "Mabusela",
                "0763728303",
                "agnes@gmail.com",
                "password123",
                address
        );
    }

    @Test
    void a_create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Customer> postResponse = restTemplate.postForEntity(url, customer, Customer.class);
        assertNotNull(postResponse);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        customer = postResponse.getBody();
        assertNotNull(customer);
        assertNotNull(customer.getId());
        System.out.println("Created: " + customer);
    }

    @Test
    void b_read() {
        String url = BASE_URL + "/read/" + customer.getId();
        ResponseEntity<Customer> response = restTemplate.getForEntity(url, Customer.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(customer.getId(), response.getBody().getId());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    void c_update() {
        Customer updatedCustomer = new Customer.Builder()
                .copy(customer)
                .setPhoneNumber("0738739399")
                .build();

        String url = BASE_URL + "/update";
        HttpEntity<Customer> request = new HttpEntity<>(updatedCustomer);
        ResponseEntity<Customer> response = restTemplate.exchange(url, HttpMethod.PUT, request, Customer.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("0738739399", response.getBody().getPhoneNumber());
        System.out.println("Updated: " + response.getBody());
        customer = response.getBody();
    }

    @Test
    void d_getAll() {
        String url = BASE_URL + "/getAll";
        ResponseEntity<Customer[]> response = restTemplate.getForEntity(url, Customer[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);

        System.out.println("All Customers:");
        for (Customer c : response.getBody()) {
            System.out.println(c);
        }
    }

    @Test
    void e_delete() {
        String url = BASE_URL + "/delete/" + customer.getId();
        restTemplate.delete(url);

        ResponseEntity<Customer> response = restTemplate.getForEntity(BASE_URL + "/read/" + customer.getId(), Customer.class);
        assertTrue(response.getStatusCode().is4xxClientError());
        System.out.println("Deleted: Customer ID " + customer.getId());
    }
}
