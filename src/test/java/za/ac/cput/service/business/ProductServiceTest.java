package za.ac.cput.service.business;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.business.Brands;
import za.ac.cput.domain.business.Product;
import za.ac.cput.factory.business.ProductFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class ProductServiceTest {

    @Autowired
    private ProductService productService; // Interface not needed since directly autowiring ProductService

    private static Product product = ProductFactory.createProduct(
            1,
            "Hydrating Cream",
            "Innisfree moisturizing cream",
            29.99,
            50,
            100,
            Brands.INNISFREE
    );

    @Test
    void a_create() {
        Product created = productService.create(product);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void b_read() {
        Product read = productService.read(product.getId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        Product updatedProduct = new Product.Builder()
                .copy(product)
                .setName("Updated Hydrating Cream")
                .setDescription("Enhanced Innisfree moisturizing cream")
                .setPrice(34.99)
                .setQuantity(75)
                .build();
        Product updated = productService.update(updatedProduct);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void d_delete() {
        productService.delete(product.getId());
        Product deleted = productService.read(product.getId());
        assertNull(deleted);
        System.out.println("Product deleted");
    }

    @Test
    void e_getAll() {
        System.out.println(productService.getAll());
    }


}