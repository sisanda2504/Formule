package za.ac.cput.service.business;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.business.Cart;
import za.ac.cput.domain.business.CartItems;
import za.ac.cput.domain.business.Product;
import za.ac.cput.domain.business.Brands;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.business.CartItemsFactory;
import za.ac.cput.factory.business.ProductFactory;
import za.ac.cput.factory.users.CustomerFactory;
import za.ac.cput.factory.business.CartFactory;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CartItemServiceTest {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;

    private static CartItems cartItem;
    private static Cart cart;

    @BeforeAll
    static void setUp(@Autowired CartService cartService) {
        Customer customer = CustomerFactory.createCustomer(
                "Annah",
                "Manda",
                "0712345678",
                "mana@gmail.com",
                "Pass1234",
                null
        );

        Product product = ProductFactory.createProduct(
                1,
                "Cleanser",
                "Face wash",
                100.0,
                10,
                1,
                Brands.INNISFREE
        );

        cart = CartFactory.createCart(1,customer, Collections.emptyList(), 0.0);
        cart = cartService.create(cart);

        cartItem = CartItemsFactory.createCartItems(product, cart, 2, 200.0);
    }

    @Test
    void a_create() {
        CartItems created = cartItemService.create(cartItem);
        assertNotNull(created);
        assertNotNull(created.getId());
        cartItem = created;
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        CartItems read = cartItemService.read(cartItem.getId());
        assertNotNull(read);
        assertEquals(cartItem.getId(), read.getId());
        System.out.println("Read: " + read);
    }

    @Test
    void c_update() {
        CartItems updatedItem = new CartItems.Builder()
                .copy(cartItem)
                .setQuantity(3)
                .setItemTotal(300.0)
                .build();

        CartItems updated = cartItemService.update(updatedItem);
        assertNotNull(updated);
        assertEquals(3, updated.getQuantity());
        assertEquals(300.0, updated.getItemTotal());
        System.out.println("Updated: " + updated);
    }

    @Test
    void d_findByCartId() {
        List<CartItems> items = cartItemService.findByCartId(cart.getId());
        assertFalse(items.isEmpty());
        System.out.println("Items in Cart ID " + cart.getId() + ": " + items);
    }

    @Test
    void e_getAll() {
        List<CartItems> allItems = cartItemService.getAll();
        assertNotNull(allItems);
        assertFalse(allItems.isEmpty());
        System.out.println("All Cart Items: " + allItems);
    }

    @Test
    void f_delete() {
        boolean deleted = cartItemService.delete(cartItem.getId());
        assertTrue(deleted);
        CartItems afterDelete = cartItemService.read(cartItem.getId());
        assertNull(afterDelete);
        System.out.println("Deleted cart item: " + cartItem.getId());
    }
}
