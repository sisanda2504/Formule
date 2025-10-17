package za.ac.cput.controller.users;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.users.CustomerFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerControllerTest {

    private static Customer customer;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/formule/customer";

    @BeforeAll
    static void setUp() {
        customer = CustomerFactory.createCustomer(
                "Agnes",
                "Mabusela",
                "0763728303",
                "agnes@gmail.com",
                "password123"
        );
    }

    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Customer> response = restTemplate.postForEntity(url, customer, Customer.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        customer = response.getBody();
        assertNotNull(customer.getId());
        System.out.println("✅ Created: " + customer);
    }

    @Test
    @Order(2)
    void read() {
        assertNotNull(customer.getId(), "Customer must exist before read test");
        String url = BASE_URL + "/read/" + customer.getId();

        ResponseEntity<Customer> response = restTemplate.getForEntity(url, Customer.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        System.out.println("📦 Read: " + response.getBody());
    }

    @Test
    @Order(3)
    void update() {
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
        System.out.println("🔁 Updated: " + response.getBody());
    }

    @Test
    @Order(4)
    void getAll() {
        String url = BASE_URL + "/getAll";
        ResponseEntity<Customer[]> response = restTemplate.getForEntity(url, Customer[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);

        System.out.println("📃 All Customers:");
        for (Customer c : response.getBody()) {
            System.out.println(c);
        }
    }

    @Test
    @Order(5)
    void delete() {
        String url = BASE_URL + "/delete/" + customer.getId();
        restTemplate.delete(url);

        ResponseEntity<Customer> response = restTemplate.getForEntity(BASE_URL + "/read/" + customer.getId(), Customer.class);
        assertTrue(response.getStatusCode().is4xxClientError());
        System.out.println("🗑️ Deleted: Customer ID " + customer.getId());
    }
}