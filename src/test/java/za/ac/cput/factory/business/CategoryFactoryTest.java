package za.ac.cput.factory.business;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.business.Category;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryFactoryTest {

    private static Category skinCareCategory = CategoryFactory.createCategory(
            1,
            "Skin Care Products",
            "Category for lotions, creams, cleansers, and other skincare items"
    );

    @Test
    @Order(1)
    void testCreateSkinCareCategorySuccess() {
        assertNotNull(skinCareCategory);
        assertEquals("Skin Care Products", skinCareCategory.getName());
        System.out.println(skinCareCategory.toString());
    }

    @Test
    @Order(2)
    void testCreateCategoryWithEmptyName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CategoryFactory.createCategory(
                    2,
                    "",
                    "Some description"
            );
        });
        assertEquals("Please provide a name", exception.getMessage());
        System.out.println("Exception caught: " + exception.getMessage());
    }

    @Test
    @Order(3)
    void testCreateCategoryWithNullDescription() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CategoryFactory.createCategory(
                    3,
                    "Skin Care Products",
                    null
            );
        });
        assertEquals("Please provide a description", exception.getMessage());
        System.out.println("Exception caught: " + exception.getMessage());
    }
}


