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
                "https://example.com/images/aloe-toner.jpg",
                80.00,
                15,
                4,
                Brands.HADA_LABO
        );

        assertNotNull(product);
        System.out.println("✅ Created Product 1: " + product);
    }

    @Test
    void testCreateValidProduct2() {
        Product product = ProductFactory.createProduct(
                "Brightening Vitamin C Serum",
                "Helps brighten dull skin",
                "https://example.com/images/vitamin-c-serum.jpg",
                150.00,
                10,
                5,
                Brands.THE_FACE_SHOP
        );

        assertNotNull(product);
        System.out.println("✅ Created Product 2: " + product);
    }

    @Test
    void testCreateProductFailNullName() {
        String testName = null;
        String testDescription = "This description is valid";
        String testImageUrl = "https://example.com/images/invalid.jpg";

        Product product = ProductFactory.createProduct(
                testName,
                testDescription,
                testImageUrl,
                60.00,
                8,
                3,
                Brands.INNISFREE
        );

        System.out.println("⚠️ Attempted to create Product with name=null");
        System.out.println("Factory returned: " + product);

        assertNull(product);
    }

    @Test
    void testCreateProductFailInvalidPrice() {
        Product product = ProductFactory.createProduct(
                "Hydrating Mist",
                "Mist for hydration",
                "https://example.com/images/mist.jpg",
                -20.00,
                12,
                2,
                Brands.COSRX
        );

        assertNull(product);
        System.out.println("⚠️ Attempted to create Product with invalid price. Factory returned: " + product);
    }

    @Test
    void testCreateProductFailEmptyImageUrl() {
        Product product = ProductFactory.createProduct(
                "Night Repair Cream",
                "Overnight skin repair",
                "",
                220.00,
                5,
                6,
                Brands.LANEIGE
        );

        assertNull(product);
        System.out.println("⚠️ Attempted to create Product with empty image URL. Factory returned: " + product);
    }
}
