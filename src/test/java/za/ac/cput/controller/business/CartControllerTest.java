package za.ac.cput.controller.business;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
        customer = CustomerFactory.createCustomer(
                "Sisanda",
                "Madikizela",
                "0763728303",
                "sisanda@gmail.com",
                "pass123",
                null
        );
        assertNotNull(customer);

        Product product = ProductFactory.createProduct(
                "Test Product",
                "Test Description",
                99.99,
                100,
                1,
                Brands.INNISFREE
        );
        assertNotNull(product);

        CartItems item = CartItemsFactory.createCartItems(
                product,
                null,
                2,
                199.98
        );

        assertNotNull(item);

        cartItems = Collections.singletonList(item);
        assertNotNull(cartItems);

        cartItems = Collections.singletonList(item);
        cart = CartFactory.createCart(
                customer,
                cartItems,
                199.98
        );

        assertNotNull(cart);
    }

    @Test
    void a_create() {
        ResponseEntity<Cart> postResponse = restTemplate.postForEntity(BASE_URL + "/create", cart, Cart.class);
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
                149.99,
                50,
                2,
                Brands.MISSHA
        );
        assertNotNull(updatedProduct);

        CartItems updatedItem = CartItemsFactory.createCartItems(
                updatedProduct,
                cart,
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

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Cart> requestUpdate = new HttpEntity<>(updatedCart, headers);

        ResponseEntity<Cart> response = restTemplate.exchange(
                BASE_URL + "/update",
                HttpMethod.PUT,
                requestUpdate,
                Cart.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Cart body = response.getBody();
        assertNotNull(body);
        assertEquals(updatedCart.getId(), body.getId());
        assertEquals(updatedItems.size(), body.getItems().size());
        assertEquals(updatedCart.getTotalPrice(), body.getTotalPrice());
        System.out.println("Updated: " + body);
    }

    @Test
    void d_delete() {
        restTemplate.delete(BASE_URL + "/delete/" + cart.getId());

        ResponseEntity<Cart> response = restTemplate.getForEntity(BASE_URL + "/read/" + cart.getId(), Cart.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        System.out.println("Deleted: Cart with ID " + cart.getId());
    }

    @Test
    void e_getAll() {
        ResponseEntity<Cart[]> response = restTemplate.getForEntity(BASE_URL + "/getAll", Cart[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("GetAll: ");
        for (Cart c : response.getBody()) {
            System.out.println(c);
        }
    }
}
