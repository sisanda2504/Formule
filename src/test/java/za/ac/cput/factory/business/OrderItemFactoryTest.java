package za.ac.cput.factory.business;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.business.Order;
import za.ac.cput.domain.business.OrderItem;
import za.ac.cput.domain.business.Product;
import za.ac.cput.domain.business.Brands;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.users.CustomerFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemFactoryTest {

    @Test
    void testCreateValidOrderItem() {
        // Create customer using factory
        Customer customer = CustomerFactory.createCustomer(
                "Agnes",
                "Mabusela",
                "0721234567",
                "agnes@example.com",
                "password123"
        );
        assertNotNull(customer, "Customer factory should create a valid customer");

        // Create order using factory
        Order order = OrderFactory.createOrder(
                customer,
                LocalDate.now(),
                100.0
        );
        assertNotNull(order, "Order factory should create a valid order");

        // Create product using factory
        Product product = ProductFactory.createProduct(
                "Test Product",
                "Test Description",
                "http://image.url",
                50.0,
                10,
                1,
                Brands.LANEIGE
        );
        assertNotNull(product, "Product factory should create a valid product");

        // Create OrderItem using factory
        OrderItem item = OrderItemFactory.createOrderItem(order, product, 2, 100.0);

        assertNotNull(item, "Factory should create a valid OrderItem");
        assertEquals(order, item.getOrder());
        assertEquals(product, item.getProduct());
        assertEquals(2, item.getQuantity());
        assertEquals(100.0, item.getItemTotal());
    }

    @Test
    void testCreateOrderItemWithInvalidQuantity() {
        OrderItem item = OrderItemFactory.createOrderItem(null, null, -1, 100.0);
        assertNull(item, "Factory should return null for invalid quantity");
    }

    @Test
    void testCreateOrderItemWithInvalidAmount() {
        Customer customer = CustomerFactory.createCustomer(
                "Agnes",
                "Mabusela",
                "0721234567",
                "agnes@example.com",
                "password123"
        );

        Order order = OrderFactory.createOrder(
                customer,
                LocalDate.now(),
                100.0
        );

        Product product = ProductFactory.createProduct(
                "Test Product",
                "Test Description",
                "http://image.url",
                50.0,
                10,
                1,
                Brands.HADA_LABO
        );

        // Invalid itemTotal
        OrderItem item = OrderItemFactory.createOrderItem(order, product, 2, -50.0);
        assertNull(item, "Factory should return null for invalid item total");
    }

    @Test
    void testCreateOrderItemWithNullOrderOrProduct() {
        Customer customer = CustomerFactory.createCustomer(
                "Agnes",
                "Mabusela",
                "0721234567",
                "agnes@example.com",
                "password123"
        );

        Order order = OrderFactory.createOrder(
                customer,
                LocalDate.now(),
                100.0
        );

        Product product = ProductFactory.createProduct(
                "Test Product",
                "Test Description",
                "http://image.url",
                50.0,
                10,
                1,
                Brands.SK_II
        );

        // Null order
        OrderItem item1 = OrderItemFactory.createOrderItem(null, product, 2, 100.0);
        // Null product
        OrderItem item2 = OrderItemFactory.createOrderItem(order, null, 2, 100.0);

        assertNull(item1, "Factory should return null if order is null");
        assertNull(item2, "Factory should return null if product is null");
    }
}