/*ShippingControllerTest.java
Author: Tsholofelo Mabidikane (230018165)
Date: 07 August 2025
 */
package za.ac.cput.controller.business;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.business.Order;
import za.ac.cput.domain.business.Shipping;
import za.ac.cput.domain.business.Status;
import za.ac.cput.domain.generic.Address;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.business.OrderFactory;
import za.ac.cput.factory.business.ShippingFactory;
import za.ac.cput.factory.generic.AddressFactory;
import za.ac.cput.factory.users.CustomerFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class ShippingControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static final String BASE_URL = "http://localhost:8080/formule/shipping";

    private static Shipping shipping;
    private static Customer customer;
    private static Address address;
    private static Order order;

    @BeforeAll
    public static void setUp() {
        customer = CustomerFactory.createCustomer(
                "Jane", "Doe", "0123456789", "jane@example.com", "Password@123", null
        );

        address = AddressFactory.createAddress(
                customer,
                "13 Cityville",
                "Cape Town",
                "Western Cape",
                "8000",
                "South Africa"
        );

        order = OrderFactory.createOrder(
                customer,
                LocalDate.of(2025, 10, 1),
                1500.00
        );

        // shipping created in a_create() test
    }

    @Test
    void a_create() {
        String url = BASE_URL + "/create";
        shipping = ShippingFactory.createShipping(
                order,
                "13 Cityville",
                Status.InTransit,
                LocalDate.of(2025, 10, 1),
                "346578964"
        );

        ResponseEntity<Shipping> postResponse = this.testRestTemplate.postForEntity(url, shipping, Shipping.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        shipping = postResponse.getBody(); // update shipping reference
        System.out.println("Created: " + shipping);
    }

    @Test
    void b_read() {
        String url = BASE_URL + "/read/" + shipping.getId();
        ResponseEntity<Shipping> response = this.testRestTemplate.getForEntity(url, Shipping.class);
        assertEquals(shipping.getId(), response.getBody().getId());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    void c_update() {
        Shipping updatedShipping = new Shipping.Builder()
                .copy(shipping)
                .setStatus(Status.Delivered)
                .build();

        String url = BASE_URL + "/update";
        HttpEntity<Shipping> entity = new HttpEntity<>(updatedShipping);
        ResponseEntity<Shipping> response = this.testRestTemplate.exchange(url, HttpMethod.PUT, entity, Shipping.class);

        assertNotNull(response.getBody());
        assertEquals(Status.Delivered, response.getBody().getStatus());
        System.out.println("Updated: " + response.getBody());

        // Update global reference for next test
        shipping = response.getBody();
    }

    @Test
    void d_delete() {
        String url = BASE_URL + "/delete/" + shipping.getId();
        this.testRestTemplate.delete(url);

        ResponseEntity<Shipping> response = this.testRestTemplate.getForEntity(BASE_URL + "/read/" + shipping.getId(), Shipping.class);
        assertNull(response.getBody());
        System.out.println("Deleted Shipping with ID: " + shipping.getId());
    }

    @Test
    void e_getAll() {
        String url = BASE_URL + "/getall";
        ResponseEntity<Shipping[]> response = this.testRestTemplate.getForEntity(url, Shipping[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length >= 0);
        System.out.println("All Shippings: ");
        for (Shipping s : response.getBody()) {
            System.out.println(s);
        }
    }
}