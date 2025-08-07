/*OrderControllerTest.java
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
import za.ac.cput.domain.Order;
import za.ac.cput.factory.OrderFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderControllerTest {

    private static Order order;

    @Autowired
    private TestRestTemplate restTemplate;
    private static final String BASE_URL = "http://localhost:8080/order";

    @BeforeAll
    static void setUp() {
        order = OrderFactory.createOrder(
                1,
                105,
                LocalDate.of(2025, 9, 15),
                100.00);
    }

    @Test
    void a_create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Order> postResponse = this.restTemplate.postForEntity(url, order, Order.class);
        assertNotNull(postResponse);
        Order orderSaved = postResponse.getBody();
        assertNotNull(orderSaved);
        assertEquals(order.getId(), orderSaved.getId());
        System.out.println("Created: " + orderSaved);
    }

    @Test
    void b_read() {
        String url = BASE_URL + "/read/" + order.getId();
        ResponseEntity<Order> response = this.restTemplate.getForEntity(url, Order.class);
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
        ResponseEntity<Order> response = this.restTemplate.postForEntity(url, updatedOrder, Order.class);
        assertNotNull(response.getBody());
        assertEquals(updatedOrder.getTotalAmount(), response.getBody().getTotalAmount());
        System.out.println("Updated: " + response.getBody());
    }

    @Test
    void d_delete() {
        String url = BASE_URL + "/delete/" + order.getId();
        this.restTemplate.delete(url);
        ResponseEntity<Order> response = this.restTemplate.getForEntity(url, Order.class);
        assertNull(response.getBody());
        System.out.println("Deleted order with ID: " + "true");
    }

    @Test
    void e_getAll() {
        String url = BASE_URL + "/getAll";
        ResponseEntity<Order[]> response = this.restTemplate.getForEntity(url, Order[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        System.out.println("All orders: " + response.getBody().length);
    }
}