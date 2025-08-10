package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class CartControllerTest {

    private static Cart cart;
    private static Customer customer;
    private static List<CartItems> cartItems;

    @Autowired
    private TestRestTemplate restTemplate;
    private static final String BASE_URL = "http://localhost:8080/formule/cart";

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
        assertNotNull(customer, "Customer creation failed in setup");

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
        assertNotNull(product, "Product creation failed in setup");

        // Create CartItems
        CartItems item = CartItemsFactory.createCartItems(
                product,
                2,
                199.98 // 2 * 99.99
        );
        assertNotNull(item, "CartItems creation failed in setup");
        cartItems = Collections.singletonList(item);
        assertNotNull(cartItems, "CartItems list creation failed in setup");

        // Create Cart
        cart = CartFactory.createCart(
                1,
                customer,
                cartItems,
                199.98
        );
        assertNotNull(cart, "Cart creation failed in setup");
    }

    @Test
    void a_create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Cart> postResponse = restTemplate.postForEntity(url, cart, Cart.class);
        assertNotNull(postResponse);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        Cart cartSaved = postResponse.getBody();
        assertNotNull(cartSaved);
        assertEquals(cart.getId(), cartSaved.getId());
        assertEquals(cart.getCustomer().getId(), cartSaved.getCustomer().getId());
        assertEquals(cart.getItems().size(), cartSaved.getItems().size());
        assertEquals(cart.getTotalPrice(), cartSaved.getTotalPrice());
        System.out.println("Created: " + cartSaved);
    }

    @Test
    void b_read() {
        String url = BASE_URL + "/read/" + cart.getId();
        ResponseEntity<Cart> response = restTemplate.getForEntity(url, Cart.class);
        assertNotNull(response.getBody());
        assertEquals(cart.getId(), response.getBody().getId());
        assertEquals(cart.getCustomer().getId(), response.getBody().getCustomer().getId());
        System.out.println("Read: " + response.getBody());
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
        assertNotNull(updatedProduct, "Updated product creation failed");

        CartItems updatedItem = CartItemsFactory.createCartItems(
                updatedProduct,
                1,
                149.99
        );
        assertNotNull(updatedItem, "Updated CartItems creation failed");

        List<CartItems> updatedItems = Collections.singletonList(updatedItem);
        Cart updatedCart = new Cart.Builder()
                .copy(cart)
                .setItems(updatedItems)
                .setTotalPrice(149.99)
                .build();

        String url = BASE_URL + "/update";
        ResponseEntity<Cart> response = restTemplate.postForEntity(url, updatedCart, Cart.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedCart.getId(), response.getBody().getId());
        assertEquals(updatedItems.size(), response.getBody().getItems().size());
        assertEquals(updatedCart.getTotalPrice(), response.getBody().getTotalPrice());
        System.out.println("Updated: " + response.getBody());
    }

    @Test
    void d_delete() {
        String url = BASE_URL + "/delete/" + cart.getId();
        restTemplate.delete(url);

        ResponseEntity<Cart> response = restTemplate.getForEntity(BASE_URL + "/read/" + cart.getId(), Cart.class);
        assertNull(response.getBody());
        System.out.println("Deleted: " + response.getBody());
    }

    @Test
    void e_getAll() {
        String url = BASE_URL + "/getAll";
        ResponseEntity<Cart[]> response = restTemplate.getForEntity(url, Cart[].class);
        assertNotNull(response.getBody());
        System.out.println("GetAll: ");
        for (Cart c : response.getBody()) {
            System.out.println(c);
        }
    }
}