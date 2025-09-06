package za.ac.cput.service.business;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class CartServiceTest {

    @Autowired
    private CartService cartService;

    private static Cart cart;
    private static Customer customer;
    private static List<CartItems> cartItems;

    @BeforeAll
    static void setUp() {
        customer = CustomerFactory.createCustomer(
                "Sisanda",
                "Madikizela",
                "0763728303",
                "Sisanda@gmail.com",
                "securePass2025"
        );
        assertNotNull(customer);

        Product product = ProductFactory.createProduct(
                "Test Product",
                "Test Description",
                "https://example.com/product-image.jpg",
                99.99,
                100,
                1,
                Brands.INNISFREE
        );

        assertNotNull(product);

        Cart tempCart = new Cart.Builder()
                .setCustomer(customer)
                .setItems(null)
                .build();

        CartItems item = CartItemsFactory.createCartItems(
                product,
                tempCart,
                2
        );
        assertNotNull(item);

        cartItems = Collections.singletonList(item);


        cart = CartFactory.createCart(
                customer,
                cartItems
        );


        item = new CartItems.Builder()
                .copy(item)
                .setCart(cart)
                .build();

        cartItems = Collections.singletonList(item);
        cart = new Cart.Builder()
                .copy(cart)
                .setItems(cartItems)
                .build();
    }

    @Test
    void a_create() {
        Cart created = cartService.create(cart);
        assertNotNull(created);
        assertEquals(cart.getCustomer().getId(), created.getCustomer().getId());
        assertEquals(cart.getItems().size(), created.getItems().size());
        assertEquals(cart.getTotalPrice(), created.getTotalPrice());
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        Cart read = cartService.read(cart.getId());
        assertNotNull(read);
        assertEquals(cart.getCustomer().getId(), read.getCustomer().getId());
        System.out.println("Read: " + read);
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
        assertNotNull(updatedProduct);

        CartItems updatedItem = CartItemsFactory.createCartItems(
                updatedProduct,
                cart,
                1
        );
        assertNotNull(updatedItem);

        List<CartItems> updatedItems = Collections.singletonList(updatedItem);

        Cart updatedCart = new Cart.Builder()
                .copy(cart)
                .setItems(updatedItems)
                .build();

        Cart updated = cartService.update(updatedCart);
        assertNotNull(updated);
        assertEquals(updatedItems.size(), updated.getItems().size());
        assertEquals(149.99, updated.getTotalPrice());
        System.out.println("Updated: " + updated);
    }

    @Test
    void d_delete() {
        cartService.delete(cart.getId());
        Cart read = cartService.read(cart.getId());
        assertNull(read);
        System.out.println("Deleted cart with ID: " + cart.getId());
    }

    @Test
    void e_getAll() {
        List<Cart> carts = cartService.getAll();
        assertNotNull(carts);
        System.out.println("All Carts:");
        for (Cart c : carts) {
            System.out.println(c);
        }
    }
}
