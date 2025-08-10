package za.ac.cput.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Brands;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItems;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Product;
import za.ac.cput.factory.CartFactory;
import za.ac.cput.factory.CartItemsFactory;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.ProductFactory;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class CartServiceTest {

    @Autowired
    private CartService cartService;

    private static Cart cart;
    private static Customer customer;
    private static List<CartItems> cartItems;

    @BeforeAll
    static void setUp() {
        // Create Customer
        customer = CustomerFactory.createCustomer(
                "Agnes",
                "Mabusela",
                "0763728303",
                "agnes@gmail.com",
                "password123",
                123
        );
        assertNotNull(customer);

        // Create Product
        Product product = ProductFactory.createProduct(
                1,
                "Test Product",
                "Test Description",
                99.99,
                100,
                1,
                Brands.INNISFREE
        );
        assertNotNull(product);

        // Create CartItems
        CartItems item = CartItemsFactory.createCartItems(
                product,
                2,
                199.98 // 2 * 99.99
        );
        assertNotNull(item);
        cartItems = Collections.singletonList(item);
        assertNotNull(cartItems);

        // Create Cart
        cart = CartFactory.createCart(
                1,
                customer,
                cartItems,
                199.98
        );
        assertNotNull(cart);
    }

    @Test
    void a_create() {
        Cart created = cartService.create(cart);
        assertNotNull(created);
        assertEquals(cart.getId(), created.getId());
        assertEquals(cart.getCustomer().getId(), created.getCustomer().getId());
        assertEquals(cart.getItems().size(), created.getItems().size());
        assertEquals(cart.getTotalPrice(), created.getTotalPrice());
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        Cart read = cartService.read(cart.getId());
        assertNotNull(read);
        assertEquals(cart.getId(), read.getId());
        assertEquals(cart.getCustomer().getId(), read.getCustomer().getId());
        System.out.println("Read: " + read);
    }

    @Test
    void c_update() {
        Product updatedProduct = ProductFactory.createProduct(
                2,
                "Updated Product",
                "Updated Desc",
                149.99,
                50,
                2,
                Brands.MISSHA
        );
        assertNotNull(updatedProduct);

        CartItems updatedItem = CartItemsFactory.createCartItems(
                updatedProduct,
                1,
                149.99
        );
        assertNotNull(updatedItem);

        List<CartItems> updatedItems = Collections.singletonList(updatedItem);
        Cart updatedCart = new Cart.Builder()
                .copy(cart)
                .setItems(updatedItems)
                .setTotalPrice(149.99)
                .build();

        Cart updated = cartService.update(updatedCart);
        assertNotNull(updated);
        assertEquals(cart.getId(), updated.getId());
        assertEquals(updatedItems.size(), updated.getItems().size());
        assertEquals(149.99, updated.getTotalPrice());
        System.out.println("Updated: " + updated);
    }

    @Test
    void d_delete() {
        cartService.delete(cart.getId());
        Cart read = cartService.read(cart.getId());
        assertNull(read);
        System.out.println("Deleted: " + read);
    }

    @Test
    void e_getAll() {
        List<Cart> carts = cartService.getAll();
        assertNotNull(carts);
        System.out.println("GetAll: ");
        for (Cart c : carts) {
            System.out.println(c);
        }
    }
}