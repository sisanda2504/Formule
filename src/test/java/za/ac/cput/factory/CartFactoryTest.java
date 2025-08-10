/*
 * CartFactoryTest.java
 * Test class for CartFactory
 * Author: Sisanda Madikizela (230601774)
 * Date: 05/08/2025
 */
package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItems;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartFactoryTest {

    private Customer customer;
    private List<CartItems> validItems;
    private Product product;
    private Cart cart1;
    private Cart cart2;
    private Cart cart3;

    @BeforeEach
    void setUp() {

        product = new Product.Builder()
                .setId(1)
                .setName("Test Product")
                .setPrice(50.0)
                .build();


        customer = CustomerFactory.createCustomer(
                "John", "Doe", "0781234567", "john.doe@example.com", "SecurePass123", 12
        );


        validItems = new ArrayList<>();
        CartItems cartItem = new CartItems.Builder()
                .setProduct(product)
                .setQuantity(2)
                .setTotalItems(100.0)
                .build();
        validItems.add(cartItem);


        cart1 = CartFactory.createCart(1, customer, validItems, 250.00);
        cart2 = CartFactory.createCart(-2, customer, validItems, 350.00);
        cart3 = CartFactory.createCart(4, customer, validItems, -500.00);
    }

    @Test
    void testCreateCartSuccess() {
        assertNotNull(cart1);
        System.out.println(cart1);
    }

    @Test
    void testCreateCartWithInvalidId() {
        assertNull(cart2);
        System.out.println(" Cart creation failed due to invalid ID:"+ cart2 );
    }

    @Test
    void testCreateCartWithInvalidPrice() {
        assertNull(cart3);
        System.out.println(" Cart with invalid price cannot be created:" +cart3);
    }

    @Test
    void testCreateCartWithAllValidData() {
        Cart validCart = CartFactory.createCart(5, customer, validItems, 300.00);
        assertNotNull(validCart);
        System.out.println("Cart ID: " + validCart.getId());
        System.out.println("Customer: " + validCart.getCustomer());
        System.out.println("Items: " + validCart.getItems());
        System.out.println("Total Price: " + validCart.getTotalPrice());
    }
}