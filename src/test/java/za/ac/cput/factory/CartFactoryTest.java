/*
 * CartFactoryTest.java
 * Test class for CartFactory
 * Author: Sisanda Madikizela (230601774)
 * Date: 18/05/2025
 */
package za.ac.cput.factory;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Brands;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItems;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CartFactoryTest {

    @Test
    void createCart() {
        // Arrange
        Customer customer = CustomerFactory.createCustomer(
                1, "John", "Doe", "+27123456789", "john.doe@company.com", "P@ssw0rd123!", 1
        );
        Product product = ProductFactory.createProduct(
                1001, "Innisfree Green Tea Serum", "Hydrating serum", 250.00, 10, 101, Brands.INNISFREE
        );
        CartItems cartItem = CartItemsFactory.createCartItems(product, 2, 250.00 * 2);
        List<CartItems> items = new ArrayList<>();
        items.add(cartItem);
        Double totalPrice = 500.00; // Sum of cartItem.totalItems

        // Act
        Cart cart = CartFactory.createCart(1, customer, items, totalPrice);

        // Assert
        assertNotNull(cart);
        System.out.println(cart);
    }

    @Test
    void createCartWithInvalidData() {
        // Arrange
        Customer customer = CustomerFactory.createCustomer(
                1, "John", "Doe", "+27123456789", "john.doe@company.com", "P@ssw0rd123!", 1
        );
        Product product = ProductFactory.createProduct(
                1001, "Innisfree Green Tea Serum", "Hydrating serum", 250.00, 10, 101, Brands.INNISFREE
        );
        CartItems cartItem = CartItemsFactory.createCartItems(product, 2, 250.00 * 2);
        List<CartItems> items = new ArrayList<>();
        items.add(cartItem);
        Double totalPrice = 0.0; // Invalid totalPrice

        // Act
        Cart cart = CartFactory.createCart(2, customer, items, totalPrice);

        // Assert
        assertNull(cart);
        System.out.println(cart);
    }

    @Test
    void createCartWithAllAttributes() {
        // Arrange
        Customer customer = CustomerFactory.createCustomer(
                2, "Jane", "Smith", "+27987654321", "jane.smith@company.com", "P@ssw0rd456!", 2
        );
        Product product = ProductFactory.createProduct(
                1002, "Missha Time Revolution Essence", "Anti-aging essence", 350.00, 5, 102, Brands.MISSHA
        );
        CartItems cartItem = CartItemsFactory.createCartItems(product, 3, 350.00 * 3);
        List<CartItems> items = new ArrayList<>();
        items.add(cartItem);
        Double totalPrice = 1050.00; // Sum of cartItem.totalItems

        // Act
        Cart cart = CartFactory.createCart(3, customer, items, totalPrice);

        // Assert
        assertNotNull(cart);
        System.out.println(cart);
    }

    @Test
    @Disabled
    void testNotImplementedYet() {
        // Not implemented yet
    }
}