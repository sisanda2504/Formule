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
                "https://example.com/product-image.jpg",
                89.99,
                20,
                1,
                Brands.HADA_LABO);

        customer = CustomerFactory.createCustomer(
                "Luna",
                "Sithole",
                "0780001122",
                "luna@gmail.com",
                "StrangerDanger123");

        cart = CartFactory.createCart(customer, Collections.emptyList());
    }

    @Test
    void testCreateCartItemsSuccess() {

        double expectedItemTotal = product.getPrice() * 2;  // 89.99 * 2 = 179.98

        CartItems cartItem = CartItemsFactory.createCartItems(product, cart, 2);
        assertNotNull(cartItem);
        assertEquals(2, cartItem.getQuantity());
        assertEquals(expectedItemTotal, cartItem.getItemTotal(), 0.001);
        assertEquals(product, cartItem.getProduct());
        assertEquals(cart, cartItem.getCart());
        System.out.println("Success: " + cartItem);
    }

    @Test
    void testCreateCartItemsWithNullProduct() {
        CartItems cartItem = CartItemsFactory.createCartItems(null, cart, 1);
        assertNull(cartItem);
        System.out.println("CartItem creation failed due to null product.");
    }

    @Test
    void testCreateCartItemsWithNullCart() {
        CartItems cartItem = CartItemsFactory.createCartItems(product, null, 1);
        assertNull(cartItem);
        System.out.println("CartItem creation failed due to null cart.");
    }

    @Test
    void testCreateCartItemsWithInvalidQuantity() {
        CartItems cartItem = CartItemsFactory.createCartItems(product, cart, 0);
        assertNull(cartItem);
        System.out.println("CartItem creation failed due to invalid quantity.");
    }

    @Test
    void testCreateCartItemsWithInvalidProductPrice() {

        product = new Product.Builder()
                .setName("Gentle Cleanser")
                .setDescription("Mild face cleanser")
                .setImage_url("https://example.com/product-image.jpg")
                .setPrice(-1)
                .setStockQuantity(20)
                .setCategoryId(1)
                .setBrand(Brands.HADA_LABO)
                .build();

        CartItems cartItem = CartItemsFactory.createCartItems(product, cart, 2);
        assertNull(cartItem);
        System.out.println("CartItem creation failed due to invalid product price.");
    }

}
