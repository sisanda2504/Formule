/*ShippingControllerTest.java
Author: Tsholofelo Mabidikane (230018165)
Date: 07 August 2025
 */
package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Shipping;
import za.ac.cput.factory.ShippingFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class ShippingControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    private static final String Base_URL = "http://localhost:8080/shipping";

    private static Shipping shipping;
    private static Shipping updatedShipping;

    @BeforeAll
    public static void setUp() {
        shipping = ShippingFactory.createShipping(
                1,
                105,
                "13 Cityville",
                "Pending",
                LocalDate.of(2025, 10, 1),
                "346578964");
    }

    @Test
    void a_create() {
        String url = Base_URL + "/create";
        ResponseEntity<Shipping> postResponse = this.testRestTemplate.postForEntity(url, shipping, Shipping.class);
        assertNotNull(postResponse);
        Shipping shippingSaved = postResponse.getBody();
        assertEquals(shipping.getId(), shippingSaved.getId());
        System.out.println("Created: " + shippingSaved);
    }

    @Test
    void b_read() {
        String url = Base_URL + "/read/" + shipping.getId();
        ResponseEntity<Shipping> response = this.testRestTemplate.getForEntity(url, Shipping.class);
        assertEquals(shipping.getId(), response.getBody().getId());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    void c_update() {
        Shipping updatedShipping = new Shipping.Builder()
                .copy(shipping)
                .setStatus("Shipped")
                .build();
        String url = Base_URL + "/update";
        ResponseEntity<Shipping> response = this.testRestTemplate.postForEntity(url, updatedShipping, Shipping.class);
        Shipping shippingUpdated = response.getBody();
        assertNotNull(response.getBody());
        assertEquals(updatedShipping.getId(), response.getBody().getId());
        System.out.println("Updated: " + shippingUpdated);
    }

    @Test
    void d_delete() {
        String url = Base_URL + "/delete/" + shipping.getId();
        this.testRestTemplate.delete(url);
        ResponseEntity<Shipping> response = this.testRestTemplate.getForEntity(url, Shipping.class);
        assertNull(response.getBody());
        System.out.println("Deleted: " + "true");
    }

    @Test
    void e_getAll() {
        String url = Base_URL + "getAll";
        ResponseEntity<Shipping[]> response = this.testRestTemplate.getForEntity(url, Shipping[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        System.out.println("Get All: " + response.getBody());

    }

}