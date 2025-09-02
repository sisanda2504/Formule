package za.ac.cput.factory.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.business.Cart;
import za.ac.cput.domain.business.CartItems;
import za.ac.cput.domain.business.Product;
import za.ac.cput.domain.business.Brands;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.users.CustomerFactory;
import za.ac.cput.factory.business.ProductFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartFactoryTest {

    private Customer customer;
    private List<CartItems> validItems;
    private Product product;
    private Cart cart1;
    private Cart cart3;

    @BeforeEach
    void setUp() {
        product = ProductFactory.createProduct(
                "Hydrating Cream",
                "Moisturizes skin",
                50.00,
                2,
                1,
                Brands.INNISFREE
        );

        customer = CustomerFactory.createCustomer(
                "Sisanda",
                "Madikizela",
                "0781234567",
                "sisanda.madikizela@gmail.com",
                "SecurePass123",
                null
        );

        validItems = new ArrayList<>();
        CartItems cartItem = new CartItems.Builder()
                .setProduct(product)
                .setQuantity(2)
                .setItemTotal(100.0)
                .build();
        validItems.add(cartItem);

        cart1 = CartFactory.createCart(customer, validItems, 250.00);
        cart3 = CartFactory.createCart(customer, validItems, -500.00);  // invalid price
    }

    @Test
    void testCreateCartSuccess() {
        assertNotNull(cart1);
        System.out.println(cart1);
    }

    @Test
    void testCreateCartWithInvalidPrice() {
        assertNull(cart3);
        System.out.println("Cart with invalid price cannot be created: " + cart3);
    }

    @Test
    void testCreateCartWithAllValidData() {
        Cart validCart = CartFactory.createCart(customer, validItems, 300.00);
        assertNotNull(validCart);
        System.out.println("Cart ID: " + validCart.getId());
        System.out.println("Customer: " + validCart.getCustomer());
        System.out.println("Items: " + validCart.getItems());
        System.out.println("Total Price: " + validCart.getTotalPrice());
    }
}
