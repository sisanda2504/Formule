package za.ac.cput.factory.business;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.business.Category;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryFactoryTest {

    private static final Category cleansersCategory = CategoryFactory.createCategory(
            "Cleansers",
            "Gentle facial cleansers designed to remove dirt, oil, and makeup without stripping the skin's natural moisture."
    );

    private static final Category emptyCategory = CategoryFactory.createCategory(
            "Serums",
            ""
    );

    private static final Category invalidCategoryNoName = CategoryFactory.createCategory(
            null,
            "Targeted treatments formulated with active ingredients to address specific skin concerns."
    );

    @Test
    @Order(1)
    void testCreateValidCategory() {
        assertNotNull(cleansersCategory);
        System.out.println("Created category: " + cleansersCategory);
    }

    @Test
    @Order(2)
    void testCreateCategoryWithEmptyDescription() {
        assertNull(emptyCategory);
        System.out.println("Failed to create category with empty description (as expected).");
    }

    @Test
    @Order(3)
    void testCreateCategoryWithNoName() {
        assertNull(invalidCategoryNoName);
        System.out.println("Failed to create category with null name (as expected).");
    }
}
