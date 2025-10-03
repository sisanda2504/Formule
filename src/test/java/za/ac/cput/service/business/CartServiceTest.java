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
import za.ac.cput.repository.business.CartItemsRepository;
import za.ac.cput.repository.business.CartRepository;
import za.ac.cput.repository.business.ProductRepository;
import za.ac.cput.repository.users.CustomerRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class CartServiceTest {

    @Autowired
    private CartService cartService;

    private static Customer customer;
    private static Product product;
    private static Cart cart;

    @BeforeAll
    static void setUp(@Autowired CustomerRepository customerRepository,
                      @Autowired ProductRepository productRepository,
                      @Autowired CartRepository cartRepository,
                      @Autowired CartItemsRepository cartItemsRepository) {

        // Create and save customer
        customer = CustomerFactory.createCustomer(
                "Sisanda",
                "Madikizela",
                "0763728303",
                "Sisanda@gmail.com",
                "securePass2025"
        );
        customer = customerRepository.save(customer);

        // Create and save product
        product = ProductFactory.createProduct(
                "Test Product",
                "Test Description",
                "https://example.com/product-image.jpg",
                99.99,
                100,
                1,
                Brands.INNISFREE
        );
        product = productRepository.save(product);

        // Create cart with customer, initially empty items
        cart = new Cart.Builder()
                .setCustomer(customer)
                .setItems(Collections.emptyList())
                .build();

        cart = cartRepository.save(cart);

        // Create and save CartItems linking product & cart
        CartItems item = CartItemsFactory.createCartItems(
                product,
                cart,
                2
        );
        item = cartItemsRepository.save(item);

        // Update cart with this item
        cart = new Cart.Builder()
                .copy(cart)
                .setItems(Collections.singletonList(item))
                .build();

        cart = cartRepository.save(cart);
    }

    @Test
    void a_create() {
        assertNotNull(cart);
        assertEquals(customer.getId(), cart.getCustomer().getId());
        assertFalse(cart.getItems().isEmpty());
        assertEquals(2 * product.getPrice(), cart.getTotalPrice());
        System.out.println("Created: " + cart);
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
        // Create new product and save it
        Product updatedProduct = ProductFactory.createProduct(
                "Updated Product",
                "Updated Desc",
                "https://example.com/images/hydrating-cream.jpg",
                149.99,
                50,
                2,
                Brands.MISSHA
        );
        // Save updated product if required by your setup
        // productRepository.save(updatedProduct);

        // Create new CartItems with updated product
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

        Cart updated = cartService.update(updatedCart);
        assertNotNull(updated);
        assertEquals(1, updated.getItems().size());
        assertEquals(149.99, updated.getTotalPrice());
        System.out.println("Updated: " + updated);
    }

    @Test
    void d_delete() {
        boolean deleted = cartService.delete(cart.getId());
        assertTrue(deleted);

        Cart read = cartService.read(cart.getId());
        assertNull(read);
        System.out.println("Deleted cart with ID: " + cart.getId());
    }

    @Test
    void e_getAll() {
        List<Cart> carts = cartService.getAll();
        assertNotNull(carts);
        System.out.println("All Carts:");
        carts.forEach(System.out::println);
    }
}
