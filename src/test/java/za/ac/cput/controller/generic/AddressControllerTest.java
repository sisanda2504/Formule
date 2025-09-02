package za.ac.cput.controller.generic;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.generic.Address;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.generic.AddressFactory;
import za.ac.cput.factory.users.CustomerFactory;
import za.ac.cput.service.users.ICustomerService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ICustomerService customerService;

    private static Customer customer;
    private static Address address;

    private static final String BASE_URL = "http://localhost:8080/formule/address";

    @BeforeAll
    static void init(@Autowired ICustomerService customerService) {
        customer = CustomerFactory.createCustomer(
                "Samkelisiwe",
                "khanyile",
                "0833838288",
                "Samke@.com",
                "password2025",
                null
        );
        customer = customerService.create(customer);
    }

    @Test
    @Order(1)
    void create() {
        address = AddressFactory.createAddress(
                customer,
                "01 Kloof Street",
                "Cape Town",
                "Western Cape",
                "8301",
                "South Africa"
        );

        String url = BASE_URL + "/create";
        ResponseEntity<Address> response = restTemplate.postForEntity(url, address, Address.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        address = response.getBody();
        System.out.println("Created Address: " + address);
    }

    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read/" + address.getId();
        ResponseEntity<Address> response = restTemplate.getForEntity(url, Address.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Read Address: " + response.getBody());
    }

    @Test
    @Order(3)
    void update() {
        Address updatedAddress = new Address.Builder()
                .copy(address)
                .setCity("Johannesburg")
                .build();

        String url = BASE_URL + "/update";
        HttpEntity<Address> entity = new HttpEntity<>(updatedAddress);
        ResponseEntity<Address> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Address.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Johannesburg", response.getBody().getCity());
        System.out.println("Updated Address: " + response.getBody());
    }

    @Test
    @Order(4)
    void getAll() {
        String url = BASE_URL + "/getall";
        ResponseEntity<Address[]> response = restTemplate.getForEntity(url, Address[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Address> addresses = Arrays.asList(response.getBody());
        assertFalse(addresses.isEmpty());
        System.out.println("All Addresses: " + addresses);
    }

    @Test
    @Order(5)
    void delete() {
        String url = BASE_URL + "/delete/" + address.getId();
        restTemplate.delete(url);
        System.out.println("Deleted Address with ID: " + address.getId());
    }
}
