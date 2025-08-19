package za.ac.cput.controller.business;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import za.ac.cput.domain.business.Category;
import za.ac.cput.service.business.ICategoryService;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryControllerTest {
    private static ICategoryService service;
    private static CategoryController controller;
    private static Category category;

    @BeforeAll
    static void setUp() {
        service = Mockito.mock(ICategoryService.class);
        controller = new CategoryController(service);
        category = new Category.Builder()
                .setId(1)
                .setName("Skincare Products")
                .setDescription("Products for skin care and treatment")
                .build();
    }
    @Test
    @Order(1)
    void testCreate() {
        when(service.create(any(Category.class))).thenReturn(category);
        Category created = controller.create(category);
        assertNotNull(created);
        assertEquals("Skincare Products", created.getName());
        verify(service, times(1)).create(category);
        System.out.println("Created: " + created);
    }
    @Test
    @Order(2)
    void testRead() {
        when(service.read(1)).thenReturn(category);
        Category found = controller.read(1);
        assertNotNull(found);
        assertEquals(1, found.getId());
        verify(service, times(1)).read(1);
        System.out.println("Read: " + found);
    }

    @Test
    @Order(3)
    void testUpdate() {
        Category updatedCategory = new Category.Builder()
                .copy(category)
                .setDescription("Updated skincare description")
                .build();

        when(service.update(any(Category.class))).thenReturn(updatedCategory);
        Category updated = controller.update(updatedCategory);
        assertNotNull(updated);
        assertEquals("Updated skincare description", updated.getDescription());
        verify(service, times(1)).update(updatedCategory);
        System.out.println("Updated: " + updated);
    }
    @Test
    @Order(4)
    void testDelete() {
        when(service.delete(1)).thenReturn(true);
        boolean result = controller.delete(1);
        assertTrue(result);
        verify(service, times(1)).delete(1);
        System.out.println("Deleted category with ID 1");
    }

    @Test
    @Order(5)
    void testGetAll() {
        when(service.getAll()).thenReturn(Arrays.asList(category));
        List<Category> all = controller.getAll();
        assertFalse(all.isEmpty());
        assertEquals(1, all.size());
        verify(service, times(1)).getAll();
        System.out.println("All categories: " + all);
    }
    @Test
    @Order(6)
    void testFindByName() {
        when(service.findByName("Skincare Products")).thenReturn(Arrays.asList(category));
        List<Category> foundByName = controller.findByName("Skincare Products");
        assertFalse(foundByName.isEmpty());
        assertEquals("Skincare Products", foundByName.get(0).getName());
        verify(service, times(1)).findByName("Skincare Products");
        System.out.println("Found by name: " + foundByName);
    }
    @Test
    @Order(7)
    void testSearchByDescription() {
        when(service.searchByDescription("skin")).thenReturn(Arrays.asList(category));
        List<Category> foundByDescription = controller.searchByDescription("skin");
        assertFalse(foundByDescription.isEmpty());
        assertTrue(foundByDescription.get(0).getDescription().toLowerCase().contains("skin"));
        verify(service, times(1)).searchByDescription("skin");
        System.out.println("Found by description: " + foundByDescription);
    }
}
