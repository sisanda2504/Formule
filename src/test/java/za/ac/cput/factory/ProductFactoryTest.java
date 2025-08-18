package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Brands;
import za.ac.cput.domain.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductFactoryTest {

    @Test
    void testCreateValidProduct1() {
        Product product = ProductFactory.createProduct(
                10,
                "Calming Aloe Toner",
                "Soothes dry and sensitive skin",
                80.00,
                15,
                4,
                Brands.HADA_LABO
        );
        // Assert factory created product
        assertNotNull(product);

        // Print created product details
        System.out.println("Created Product 1: " + product);
    }

    @Test
    void testCreateValidProduct2() {
        Product product = ProductFactory.createProduct(
                11,
                "Brightening Vitamin C Serum",
                "Helps brighten dull skin",
                150.00,
                10,
                5,
                Brands.THE_FACE_SHOP
        );
        // Assert factory created product
        assertNotNull(product);

        // Print created product details
        System.out.println("Created Product 2: " + product);
    }

    @Test
    void testCreateProductFailNullName() {
        // Create product with null name (invalid)
        String testName = null;
        String testDescription = "This description is valid";

        Product product = ProductFactory.createProduct(
                12,
                testName,
                testDescription,
                60.00,
                8,
                3,
                Brands.INNISFREE
        );

        // Print input values and factory output for clarity
        System.out.println("Attempted to create Product with name=null");
        System.out.println("Name: " + testName);
        System.out.println("Description: " + testDescription);
        System.out.println("Factory returned: " + product);

        // Assert factory returned null due to invalid name
        assertNull(product);
    }
}
