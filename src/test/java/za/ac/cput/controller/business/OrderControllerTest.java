/*OrderControllerTest.java
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
import org.springframework.http.*;
import za.ac.cput.domain.business.Order;
import za.ac.cput.domain.generic.Address;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.business.OrderFactory;
import za.ac.cput.factory.users.CustomerFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderControllerTest {

    private static Order order;
    private static Customer customer;

    @Autowired
    private TestRestTemplate restTemplate;
    private static final String BASE_URL = "http://localhost:8080/formule/order";
    @BeforeAll
    static void setUp() {
        Address address = new Address.Builder()
                .setStreet("11 Main Road")
                .setCity("Cape Town")
                .setPostalCode("7750")
                .setProvince("Western Cape")
                .build();

        customer = CustomerFactory.createCustomer(
                "Palesa",
                "Mabikane",
                "087372803",
                "p.mabidikane@icloud.com",
                "pass1738",
                address
        );

        order = OrderFactory.createOrder(
                customer,
                LocalDate.of(2025, 9, 15),
                100.00
        );
    }


    @Test
    void a_create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Order> postResponse = restTemplate.postForEntity(url, order, Order.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        Order orderSaved = postResponse.getBody();
        assertNotNull(orderSaved);
        assertNotNull(orderSaved.getId());
        System.out.println("Created: " + orderSaved);

        order = orderSaved;
    }

    @Test
    void b_read() {
        String url = BASE_URL + "/read/" + order.getId();
        ResponseEntity<Order> response = restTemplate.getForEntity(url, Order.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(order.getId(), response.getBody().getId());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    void c_update() {
        String url = BASE_URL + "/update";

        Order updatedOrder = new Order.Builder()
                .copy(order)
                .setTotalAmount(250.00)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Order> entity = new HttpEntity<>(updatedOrder, headers);

        ResponseEntity<Order> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Order.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(250.00, response.getBody().getTotalAmount());
        System.out.println("Updated: " + response.getBody());

        order = response.getBody();
    }

    @Test
    void d_delete() {
        String url = BASE_URL + "/delete/" + order.getId();

        ResponseEntity<Void> deleteResponse = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());
        System.out.println("Deleted order with ID: " + order.getId());

        ResponseEntity<Order> responseAfterDelete = restTemplate.getForEntity(BASE_URL + "/read/" + order.getId(), Order.class);
        assertEquals(HttpStatus.NOT_FOUND, responseAfterDelete.getStatusCode());
    }

    @Test
    void e_getAll() {
        String url = BASE_URL + "/getall";
        ResponseEntity<Order[]> response = restTemplate.getForEntity(url, Order[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("All orders count: " + response.getBody().length);
    }
}