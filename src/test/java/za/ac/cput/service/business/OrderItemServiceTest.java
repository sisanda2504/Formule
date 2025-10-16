package za.ac.cput.service.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.business.Order;
import za.ac.cput.domain.business.OrderItem;
import za.ac.cput.domain.business.Product;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.business.OrderItemFactory;
import za.ac.cput.factory.users.CustomerFactory;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderItemServiceTest {

    @Autowired
    private IOrderItemService orderItemService;

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
    void testCreateReadUpdateDelete() {
        // Create
        OrderItem orderItem = OrderItemFactory.createOrderItem(order, product, 2, 100.0);
        OrderItem savedItem = orderItemService.create(orderItem);
        assertNotNull(savedItem);
        assertEquals(2, savedItem.getQuantity());

        // Read
        OrderItem fetchedItem = orderItemService.read(savedItem.getId());
        assertNotNull(fetchedItem);
        assertEquals(100.0, fetchedItem.getItemTotal());

        // Update
        savedItem = new OrderItem(order, product, 3, 150.0);
        savedItem = orderItemService.update(savedItem);
        assertEquals(150.0, savedItem.getItemTotal());

        // Delete
        boolean deleted = orderItemService.delete(savedItem.getId());
        assertTrue(deleted);
        assertNull(orderItemService.read(savedItem.getId()));
    }

    @Test
    void testFindOrderItemsByOrderId() {
        OrderItem item1 = OrderItemFactory.createOrderItem(order, product, 1, 50.0);
        OrderItem item2 = OrderItemFactory.createOrderItem(order, product, 2, 100.0);

        orderItemService.create(item1);
        orderItemService.create(item2);

        List<OrderItem> items = orderItemService.findOrderItemsByOrderId(order.getId());
        assertEquals(2, items.size());
    }

    @Test
    void testGetAllOrderItems() {
        OrderItem item1 = OrderItemFactory.createOrderItem(order, product, 1, 50.0);
        OrderItem item2 = OrderItemFactory.createOrderItem(order, product, 2, 100.0);

        orderItemService.create(item1);
        orderItemService.create(item2);

        List<OrderItem> allItems = orderItemService.getAllOrderItems();
        assertTrue(allItems.size() >= 2);
    }
}