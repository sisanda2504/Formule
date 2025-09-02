package za.ac.cput.service.business;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.business.Brands;
import za.ac.cput.domain.business.Product;
import za.ac.cput.factory.business.ProductFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    private static Product product = ProductFactory.createProduct(
            "Hydrating Cream",
            "Moisturizing Night Cream",
            29.99,
            50,
            100,
            Brands.INNISFREE
    );

    private static Long createdProductId;

    @Test
    void a_create() {
        Product created = productService.create(product);
        assertNotNull(created);
        assertNotNull(created.getId());
        createdProductId = created.getId();
        System.out.println(created);
    }

    @Test
    void b_read() {
        assertNotNull(createdProductId);
        Product read = productService.read(createdProductId);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        Product updatedProduct = new Product.Builder()
                .copy(product)
                .setId(createdProductId)
                .setName("Updated Hydrating Cream")
                .setDescription("Enhanced Day & Night Moisturizing cream")
                .setPrice(34.99)
                .setQuantity(75)
                .build();

        Product updated = productService.update(updatedProduct);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void e_delete() {
        productService.delete(createdProductId);
        Product deleted = productService.read(createdProductId);
        assertNull(deleted);
        System.out.println("Product deleted");
    }

    @Test
    void d_getAll() {
        Iterable<Product> allProducts = productService.getAll();
        assertNotNull(allProducts);
        System.out.println("All Products: ");
        System.out.println(allProducts);
    }


}