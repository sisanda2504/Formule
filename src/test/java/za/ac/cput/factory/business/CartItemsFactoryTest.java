package za.ac.cput.factory.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.business.Brands;
import za.ac.cput.domain.business.Cart;
import za.ac.cput.domain.business.CartItems;
import za.ac.cput.domain.business.Product;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.users.CustomerFactory;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CartItemsFactoryTest {

    private Product product;
    private Cart cart;
    private Customer customer;

    @BeforeEach
    void setUp() {
        product = ProductFactory.createProduct(
                "Gentle Cleanser",
                "Mild face cleanser",
                89.99,
                20,
                1,
                Brands.HADA_LABO);

        customer = CustomerFactory.createCustomer(
                "Luna",
                "Sithole",
                "0780001122",
                "luna@example.com",
                "StrongPass123",
                null);

        cart = CartFactory.createCart(customer, Collections.emptyList(), 0.0);
    }

    @Test
    void testCreateCartItemsSuccess() {
        CartItems cartItem = CartItemsFactory.createCartItems(product, cart, 2, 179.98);
        assertNotNull(cartItem);
        assertEquals(2, cartItem.getQuantity());
        assertEquals(179.98, cartItem.getItemTotal());
        assertEquals(product, cartItem.getProduct());
        assertEquals(cart, cartItem.getCart());
        System.out.println("Success: " + cartItem);
    }

    @Test
    void testCreateCartItemsWithNullProduct() {
        CartItems cartItem = CartItemsFactory.createCartItems(null, cart, 1, 89.99);
        assertNull(cartItem);
        System.out.println("CartItem creation failed due to null product.");
    }

    @Test
    void testCreateCartItemsWithNullCart() {
        CartItems cartItem = CartItemsFactory.createCartItems(product, null, 1, 89.99);
        assertNull(cartItem);
        System.out.println("CartItem creation failed due to null cart.");
    }

    @Test
    void testCreateCartItemsWithInvalidQuantity() {
        CartItems cartItem = CartItemsFactory.createCartItems(product, cart, 0, 89.99);
        assertNull(cartItem);
        System.out.println("CartItem creation failed due to invalid quantity.");
    }

    @Test
    void testCreateCartItemsWithInvalidTotal() {
        CartItems cartItem = CartItemsFactory.createCartItems(product, cart, 2, -100.00);
        assertNull(cartItem);
        System.out.println("CartItem creation failed due to invalid total.");
    }
}
