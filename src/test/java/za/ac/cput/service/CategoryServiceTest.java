package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Category;
import za.ac.cput.factory.CategoryFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CategoryServiceTest {

    @Autowired
    private ICategoryService service;

    private final Category category = CategoryFactory.createCategory(
            1,
            "Skin Care Products",
            "Category for lotions, creams, cleansers, and other skincare items"
    );

    @Test
    void a_create() {
        Category created = service.create(category);
        assertNotNull(created);
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        Category read = service.read(category.getId());
        assertNotNull(read);
        System.out.println("Read: " + read);
    }

    @Test
    void c_update() {
        Category newCategory = new Category.Builder()
                .copy(category)
                .setDescription("Updated: Skincare essentials like cleansers, moisturizers, and serums")
                .build();
        Category updated = service.update(newCategory);
        assertNotNull(updated);
        System.out.println("Updated: " + updated);
    }

    @Test
    void d_getAll() {
        System.out.println("All Categories: " + service.getAll());
    }

    @Test
    void e_delete() {
        boolean deleted = service.delete(category.getId());
        assertTrue(deleted);
        System.out.println("Deleted: " + deleted);
    }
}
