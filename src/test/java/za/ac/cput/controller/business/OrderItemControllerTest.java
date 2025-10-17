package za.ac.cput.controller.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.business.Order;
import za.ac.cput.domain.business.OrderItem;
import za.ac.cput.domain.business.Product;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.business.OrderItemFactory;
import za.ac.cput.factory.users.CustomerFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderItemControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private Order order;
    private Product product;

    @BeforeEach
    void setUp() {
        Customer customer = CustomerFactory.createCustomer(
                "Tsholofelo",
                "Mabidikane",
                "0721234567",
                "tsholo@mabidikane.com",
                "183j#n2ne@1"
        );

        order = new Order.Builder()
                .setCustomer(customer)
                .setOrderDate(LocalDate.now())
                .setTotalAmount(0.0)
                .build();

        product = new Product.Builder()
                .setId(1L)
                .setName("Test Product")
                .setPrice(50.0)
                .build();
    }

    @Test
    void testCreateAndReadOrderItem() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(order, product, 2, 100.0);

        ResponseEntity<OrderItem> createResponse = restTemplate.postForEntity(
                "/api/order-items/create", orderItem, OrderItem.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        assertNotNull(createResponse.getBody());

        Long id = createResponse.getBody().getId();

        ResponseEntity<OrderItem> readResponse = restTemplate.getForEntity(
                "/api/order-items/read/" + id, OrderItem.class);
        assertEquals(HttpStatus.OK, readResponse.getStatusCode());
        assertNotNull(readResponse.getBody());
        assertEquals(2, readResponse.getBody().getQuantity());
    }

    @Test
    void testUpdateOrderItem() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(order, product, 1, 50.0);
        OrderItem created = restTemplate.postForObject("/api/order-items/create", orderItem, OrderItem.class);

        // Build a new updated OrderItem using the Builder pattern
        OrderItem updated = new OrderItem.Builder()
                .copy(created)
                .setQuantity(3)
                .setItemTotal(150.0)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OrderItem> requestUpdate = new HttpEntity<>(updated, headers);

        ResponseEntity<OrderItem> updateResponse = restTemplate.exchange(
                "/api/order-items/update", HttpMethod.PUT, requestUpdate, OrderItem.class);

        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
        assertEquals(150.0, updateResponse.getBody().getItemTotal());
        assertEquals(3, updateResponse.getBody().getQuantity());
    }

    @Test
    void testDeleteOrderItem() {
        OrderItem orderItem = OrderItemFactory.createOrderItem(order, product, 1, 50.0);
        OrderItem created = restTemplate.postForObject("/api/order-items/create", orderItem, OrderItem.class);

        restTemplate.delete("/api/order-items/delete/" + created.getId());

        ResponseEntity<OrderItem> response = restTemplate.getForEntity(
                "/api/order-items/read/" + created.getId(), OrderItem.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetAllAndByOrderId() {
        OrderItem item1 = OrderItemFactory.createOrderItem(order, product, 1, 50.0);
        OrderItem item2 = OrderItemFactory.createOrderItem(order, product, 2, 100.0);

        restTemplate.postForObject("/api/order-items/create", item1, OrderItem.class);
        restTemplate.postForObject("/api/order-items/create", item2, OrderItem.class);

        ResponseEntity<OrderItem[]> allResponse = restTemplate.getForEntity("/api/order-items/all", OrderItem[].class);
        assertTrue(allResponse.getBody().length >= 2);

        ResponseEntity<OrderItem[]> byOrderResponse = restTemplate.getForEntity(
                "/api/order-items/by-order/" + order.getId(), OrderItem[].class);
        assertEquals(2, byOrderResponse.getBody().length);
    }
}