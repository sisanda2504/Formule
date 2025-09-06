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
    private Cart cart2;

    @BeforeEach
    void setUp() {

        product = ProductFactory.createProduct(
                "Hydrating Cream",
                "Moisturizes skin",
                "https://example.com/image.jpg",
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
                "SecurePass123"
        );


        validItems = new ArrayList<>();
        CartItems cartItem = CartItemsFactory.createCartItems(product, null, 2);
        validItems.add(cartItem);


        cart1 = CartFactory.createCart(customer, validItems);
        cart2 = CartFactory.createCart(customer, new ArrayList<>());
    }

    @Test
    void testCreateCartSuccess() {
        assertNotNull(cart1);
        assertEquals(100.0, cart1.getTotalPrice());
        assertEquals(1, cart1.getItems().size());
        System.out.println("Cart with valid items: " + cart1);
    }

    @Test
    void testCreateCartWithEmptyItems() {
        assertNotNull(cart2);
        assertEquals(0.0, cart2.getTotalPrice());
        assertTrue(cart2.getItems().isEmpty());
        System.out.println("Cart with no items: " + cart2);
    }

    @Test
    void testCartTotalCalculation() {
        Cart validCart = CartFactory.createCart(customer, validItems);
        assertNotNull(validCart);
        assertEquals(100.0, validCart.getTotalPrice());
        assertEquals(1, validCart.getItems().size());
        System.out.println("Cart with valid items total: " + validCart.getTotalPrice());
    }
}
