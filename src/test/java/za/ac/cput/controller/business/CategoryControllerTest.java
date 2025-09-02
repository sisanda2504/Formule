package za.ac.cput.controller.business;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.business.Category;
import za.ac.cput.factory.business.CategoryFactory;
import za.ac.cput.service.business.ICategoryService;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private static final String BASE_URL = "http://localhost:8080/formule/category";

    @Autowired
    private ICategoryService categoryService;

    private static Category category;
    private static Category savedCategory;

    @BeforeAll
    static void init() {
        category = CategoryFactory.createCategory(
                "Skincare Products",
                "Products for skin care and treatment"
        );
    }

    @Test
    @Order(1)
    void create() {
        ResponseEntity<Category> response = restTemplate.postForEntity(BASE_URL + "/create", category, Category.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        savedCategory = response.getBody();
        assertNotNull(savedCategory.getId());
        System.out.println("Created Category: " + savedCategory);
    }

    @Test
    @Order(2)
    void read() {
        ResponseEntity<Category> response = restTemplate.getForEntity(BASE_URL + "/read/" + savedCategory.getId(), Category.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Category readCategory = response.getBody();
        assertNotNull(readCategory);
        assertEquals(savedCategory.getId(), readCategory.getId());
        System.out.println("Read Category: " + readCategory);
    }

    @Test
    @Order(3)
    void update() {
        Category updatedCategory = new Category.Builder()
                .copy(savedCategory)
                .setDescription("Updated skincare description")
                .build();

        HttpEntity<Category> entity = new HttpEntity<>(updatedCategory);
        ResponseEntity<Category> response = restTemplate.exchange(BASE_URL + "/update", HttpMethod.PUT, entity, Category.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Category updated = response.getBody();
        assertNotNull(updated);
        assertEquals("Updated skincare description", updated.getDescription());
        System.out.println("Updated Category: " + updated);
    }

    @Test
    @Order(4)
    void getAll() {
        ResponseEntity<Category[]> response = restTemplate.getForEntity(BASE_URL + "/getall", Category[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Category> categories = Arrays.asList(response.getBody());
        assertFalse(categories.isEmpty());
        System.out.println("All Categories: " + categories);
    }

    @Test
    @Order(5)
    void delete() {
        restTemplate.delete(BASE_URL + "/delete/" + savedCategory.getId());
        System.out.println("Deleted Category with ID: " + savedCategory.getId());
    }
}
