package za.ac.cput.controller.business;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.business.Brands;
import za.ac.cput.domain.business.Cart;
import za.ac.cput.domain.business.CartItems;
import za.ac.cput.domain.business.Product;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.business.CartFactory;
import za.ac.cput.factory.business.CartItemsFactory;
import za.ac.cput.factory.business.ProductFactory;
import za.ac.cput.factory.users.CustomerFactory;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CartItemControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/formule/cart-items";
    private static CartItems cartItem;
    private static Long cartItemId;
    private static Cart cart;
    private static Customer customer;

    @BeforeAll
    static void setUp() {
        customer = CustomerFactory.createCustomer(
                "Lindiwe",
                "Nkosi",
                "0781234567",
                "lindiwe@gmail.com",
                "Pass1324",
                null
        );

        Product product = ProductFactory.createProduct(
                1,
                "Glow Serum",
                "Hydrating serum",
                199.99,
                10,
                1,
                Brands.MISSHA
        );

        cart = CartFactory.createCart(1, customer, Collections.emptyList(), 0.0);

        cartItem = CartItemsFactory.createCartItems(product, cart,2, 399.98);
    }

    @Test
    @Order(1)
    void create() {
        ResponseEntity<CartItems> response = restTemplate.postForEntity(BASE_URL + "/create", cartItem, CartItems.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        cartItemId = response.getBody().getId();
        System.out.println("Created CartItem: " + response.getBody());
    }

    @Test
    @Order(2)
    void read() {
        ResponseEntity<CartItems> response = restTemplate.getForEntity(BASE_URL + "/read/" + cartItemId, CartItems.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Read CartItem: " + response.getBody());
    }

    @Test
    @Order(3)
    void update() {
        CartItems updatedCartItem = new CartItems.Builder()
                .copy(cartItem)
                .setQuantity(3)
                .setItemTotal(599.97)
                .build();

        ResponseEntity<CartItems> response = restTemplate.postForEntity(BASE_URL + "/update", updatedCartItem, CartItems.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Updated CartItem: " + response.getBody());
    }

    @Test
    @Order(4)
    void findByCartId() {
        ResponseEntity<CartItems[]> response = restTemplate.getForEntity(BASE_URL + "/findByCartId/" + cart.getId(), CartItems[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Cart Items for Cart ID: " + cart.getId());
        for (CartItems item : response.getBody()) {
            System.out.println(item);
        }
    }

    @Test
    @Order(5)
    void getAll() {
        ResponseEntity<CartItems[]> response = restTemplate.getForEntity(BASE_URL + "/getAll", CartItems[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("All CartItems:");
        for (CartItems item : response.getBody()) {
            System.out.println(item);
        }
    }

    @Test
    @Order(6)
    void delete() {
        restTemplate.delete(BASE_URL + "/delete/" + cartItemId);
        ResponseEntity<CartItems> response = restTemplate.getForEntity(BASE_URL + "/read/" + cartItemId, CartItems.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        System.out.println("Deleted CartItem with ID: " + cartItemId);
    }
}
