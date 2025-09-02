package za.ac.cput.factory.business;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.business.Brands;
import za.ac.cput.domain.business.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductFactoryTest {

    @Test
    void testCreateValidProduct1() {
        Product product = ProductFactory.createProduct(
                "Calming Aloe Toner",
                "Soothes dry and sensitive skin",
                80.00,
                15,
                4,
                Brands.HADA_LABO
        );
        assertNotNull(product);
        System.out.println("Created Product 1: " + product);
    }

    @Test
    void testCreateValidProduct2() {
        Product product = ProductFactory.createProduct(

                "Brightening Vitamin C Serum",
                "Helps brighten dull skin",
                150.00,
                10,
                5,
                Brands.THE_FACE_SHOP
        );

        assertNotNull(product);
        System.out.println("Created Product 2: " + product);
    }

    @Test
    void testCreateProductFailNullName() {
        String testName = null;
        String testDescription = "This description is valid";

        Product product = ProductFactory.createProduct(
                testName,
                testDescription,
                60.00,
                8,
                3,
                Brands.INNISFREE
        );

        System.out.println("Attempted to create Product with name=null");
        System.out.println("Name: " + testName);
        System.out.println("Description: " + testDescription);
        System.out.println("Factory returned: " + product);

        assertNull(product);
    }
}
