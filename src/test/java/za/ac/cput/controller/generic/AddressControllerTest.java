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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AddressControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ICustomerService customerService;

    private Customer customer;
    private Address address;

    private final String BASE_URL = "http://localhost:8080/formule/address";

    @BeforeAll
    void setup() {
        customer = CustomerFactory.createCustomer(
                "Samkelisiwe",
                "Khanyile",
                "0833838288",
                "samke@example.com",
                "password2025"
        );
        customer = customerService.create(customer);
        assertNotNull(customer.getId(), "Customer creation failed");
    }

    @Test
    @Order(1)
    void a_create() {
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

        System.out.println("✅ Created Address: " + address);
    }

    @Test
    @Order(2)
    void b_read() {
        String url = BASE_URL + "/read/" + address.getId();
        ResponseEntity<Address> response = restTemplate.getForEntity(url, Address.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        System.out.println("📦 Read Address: " + response.getBody());
    }

    @Test
    @Order(3)
    void c_update() {
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

        System.out.println("🔁 Updated Address: " + response.getBody());
    }

    @Test
    @Order(4)
    void d_getAll() {
        String url = BASE_URL + "/getall";
        ResponseEntity<Address[]> response = restTemplate.getForEntity(url, Address[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Address> addresses = Arrays.asList(response.getBody());
        assertFalse(addresses.isEmpty());

        System.out.println("📃 All Addresses: " + addresses);
    }

    @Test
    @Order(5)
    void e_findByCustomerId() {
        String url = BASE_URL + "/customer/" + customer.getId();
        ResponseEntity<Address[]> response = restTemplate.getForEntity(url, Address[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Address> addresses = Arrays.asList(response.getBody());
        assertFalse(addresses.isEmpty());
        assertTrue(addresses.stream().anyMatch(a -> a.getId().equals(address.getId())));

        System.out.println("📌 Addresses by customer ID: " + addresses);
    }

    @Test
    @Order(6)
    void f_delete() {
        String url = BASE_URL + "/delete/" + address.getId();
        restTemplate.delete(url);

        // Confirm it's deleted
        ResponseEntity<Address> response = restTemplate.getForEntity(BASE_URL + "/read/" + address.getId(), Address.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody()); // Assuming your controller returns null

        System.out.println("🗑️ Deleted Address with ID: " + address.getId());
    }

    // Optional: Negative test for invalid creation
    @Test
    @Order(7)
    void g_createAddressWithoutCustomer_shouldFail() {
        Address badAddress = AddressFactory.createAddress(
                null,
                "Ghost Street",
                "Nowhere",
                "NullProvince",
                "0000",
                "Neverland"
        );

        String url = BASE_URL + "/create";
        ResponseEntity<String> response = restTemplate.postForEntity(url, badAddress, String.class);

        assertTrue(response.getStatusCode().is5xxServerError() || response.getStatusCode().is4xxClientError());
        System.out.println("❌ Failed to create address without customer as expected");
    }
}
