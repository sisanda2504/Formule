package za.ac.cput.controller.business;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.business.Brands;
import za.ac.cput.domain.business.Cart;
import za.ac.cput.domain.business.CartItems;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.domain.business.Product;
import za.ac.cput.factory.business.CartFactory;
import za.ac.cput.factory.business.CartItemsFactory;
import za.ac.cput.factory.users.CustomerFactory;
import za.ac.cput.factory.business.ProductFactory;
import za.ac.cput.service.users.CustomerService;
import za.ac.cput.service.business.ProductService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CartControllerTest {

    private static Cart cart;
    private static Customer customer;
    private static Product product;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    private static final String BASE_URL = "http://localhost:8080/formule/cart";

    @BeforeAll
    void setUp() {
        // Create and persist customer
        customer = CustomerFactory.createCustomer(
                "Sisanda",
                "Madikizela",
                "0763728303",
                "sisanda@gmail.com",
                "pass123"
        );
        customer = customerService.create(customer);
        assertNotNull(customer);

        // Create and persist product
        product = ProductFactory.createProduct(
                "Test Product",
                "Test Description",
                "https://example.com/images/glow-serum.jpg",
                99.99,
                100,
                1,
                Brands.INNISFREE
        );
        product = productService.create(product);
        assertNotNull(product);

        // Prepare CartItems (cart set later)
        CartItems item = CartItemsFactory.createCartItems(
                product,
                null, // Set later
                2
        );
        assertNotNull(item);

        List<CartItems> cartItems = Collections.singletonList(item);

        // Build cart and assign to CartItems
        cart = CartFactory.createCart(customer, cartItems);

        // Set the cart reference inside each CartItem
        item = new CartItems.Builder().copy(item).setCart(cart).build();
        cartItems = Collections.singletonList(item);

        cart = new Cart.Builder()
                .copy(cart)
                .setItems(cartItems)
                .build();

        assertNotNull(cart);
    }

    @Test
    void a_create() {
        ResponseEntity<Cart> postResponse = restTemplate.postForEntity(BASE_URL + "/create", cart, Cart.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        Cart cartSaved = postResponse.getBody();
        assertNotNull(cartSaved);
        assertNotNull(cartSaved.getId());

        // Store saved cart with generated ID for later tests
        cart = cartSaved;

        assertEquals(cart.getCustomer().getId(), cartSaved.getCustomer().getId());
        assertEquals(cart.getItems().size(), cartSaved.getItems().size());
        System.out.println("Created: " + cartSaved);
    }

    @Test
    void b_read() {
        ResponseEntity<Cart> response = restTemplate.getForEntity(BASE_URL + "/read/" + cart.getId(), Cart.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Cart body = response.getBody();
        assertNotNull(body);
        assertEquals(cart.getId(), body.getId());
        assertEquals(cart.getCustomer().getId(), body.getCustomer().getId());

        System.out.println("Read: " + body);
    }

    @Test
    void c_update() {
        Product updatedProduct = ProductFactory.createProduct(
                "Updated Product",
                "Updated Desc",
                "https://example.com/images/hydrating-cream.jpg",
                149.99,
                50,
                2,
                Brands.MISSHA
        );
        updatedProduct = productService.create(updatedProduct);
        assertNotNull(updatedProduct);

        CartItems updatedItem = CartItemsFactory.createCartItems(
                updatedProduct,
                cart,
                1
        );
        List<CartItems> updatedItems = Collections.singletonList(updatedItem);

        Cart updatedCart = new Cart.Builder()
                .copy(cart)
                .setItems(updatedItems)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Cart> requestUpdate = new HttpEntity<>(updatedCart, headers);

        ResponseEntity<Cart> response = restTemplate.exchange(
                BASE_URL + "/update",
                HttpMethod.PUT,
                requestUpdate,
                Cart.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Cart updated = response.getBody();
        assertNotNull(updated);
        assertEquals(updatedItems.size(), updated.getItems().size());
        System.out.println("Updated: " + updated);

        cart = updated; // Store for delete
    }

    @Test
    void d_delete() {
        restTemplate.delete(BASE_URL + "/delete/" + cart.getId());

        ResponseEntity<Cart> response = restTemplate.getForEntity(BASE_URL + "/read/" + cart.getId(), Cart.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        System.out.println("Deleted cart with ID: " + cart.getId());
    }

    @Test
    void e_getAll() {
        ResponseEntity<Cart[]> response = restTemplate.getForEntity(BASE_URL + "/getAll", Cart[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Cart[] carts = response.getBody();
        assertNotNull(carts);

        System.out.println("All Carts:");
        for (Cart c : carts) {
            System.out.println(c);
        }
    }
}
