package za.ac.cput.factory;

import org.junit.jupiter.api.Disabled;
import za.ac.cput.domain.Brands;
import za.ac.cput.domain.Product;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductFactoryTest {
    @Test
     void createProduct() {
        Product product = ProductFactory.createProduct(3000, "Nike Air Max", "Sneakers", 1500.00, 1, 400, Brands.valueOf("INNISFREE"));

        assertNotNull(product);
        System.out.println(product);
    }
    @Test
    void createProductWithInvalidData() {
        Product product = ProductFactory.createProduct(3999, "Cavella", "", -1500.00, 1, 200, Brands.valueOf("MISSHA"));

        assertNotNull(product);
        System.out.println(product);
    }
    @Test
    void createProductWithAllAttributes() {
        Product product = ProductFactory.createProduct(3400, "Palleadeum", "Sneakers", 1800.00, 2, 300, Brands.valueOf("LANEIGE"));

        assertNotNull(product);
        System.out.println(product);
    }
    @Test
    @Disabled
    void testCreateTestNotImplementedYet() {

    }
}
