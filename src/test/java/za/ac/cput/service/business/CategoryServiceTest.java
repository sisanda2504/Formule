package za.ac.cput.service.business;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.business.Category;
import za.ac.cput.factory.business.CategoryFactory;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CategoryServiceTest {

    @Autowired
    private ICategoryService categoryService;

    private static Category category= CategoryFactory.createCategory(
            "Toners",
            "some description"
    );

    private static Category savedCategory;

    @Test
    void a_create() {
        savedCategory = categoryService.create(category);
        assertNotNull(savedCategory);
        assertNotNull(savedCategory.getId());
        System.out.println("Created: "+savedCategory);
    }

    @Test
    void b_read() {
        Category read = categoryService.read(category.getId());
        assertNotNull(read);
        System.out.println(read);

    }

    @Test
    void c_update() {
        Category newCategory = new Category.Builder()
                .copy(category)
                .setDescription("Updated description")
                .build();
        Category updated = categoryService.update(newCategory);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void e_delete() {
        boolean deleted = categoryService.delete(category.getId());
        assertTrue(deleted);
        System.out.println("Deleted: " + category);
    }

    @Test
    void d_getAll() {
        System.out.println(categoryService.getAll());
    }
}