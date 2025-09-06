package za.ac.cput.controller.business;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.business.Brands;
import za.ac.cput.domain.business.Product;
import za.ac.cput.factory.business.ProductFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class ProductControllerTest {

    private static Product product;
    private static Long productId;

    @Autowired
    private TestRestTemplate restTemplate;


    private static final String BASE_URL = "http://localhost:8080/formule/product";

    @BeforeAll
    static void setUp() {
        product = ProductFactory.createProduct(
                "Test Product",
                "Sample description",
                "https://example.com/image.jpg",
                99.99,
                50,
                1,
                Brands.INNISFREE
        );
    }

    @Test
    void a_create() {
        ResponseEntity<Product> response = restTemplate.postForEntity(BASE_URL + "/create", product, Product.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        productId = response.getBody().getId();
        assertNotNull(productId);
        System.out.println("Created: " + response.getBody());
    }

    @Test
    void b_read() {
        ResponseEntity<Product> response = restTemplate.getForEntity(BASE_URL + "/read/" + productId, Product.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    void c_update() {
        Product updatedProduct = new Product.Builder()
                .copy(product)
                .setId(productId)
                .setName("Updated Product")
                .setPrice(120.00)
                .build();

        HttpEntity<Product> request = new HttpEntity<>(updatedProduct);
        ResponseEntity<Product> response = restTemplate.exchange(BASE_URL + "/update", HttpMethod.PUT, request, Product.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Updated: " + response.getBody());
    }

    @Test
    void d_getAll() {
        ResponseEntity<Product[]> response = restTemplate.getForEntity(BASE_URL + "/getall", Product[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("All Products:");
        for (Product p : response.getBody()) {
            System.out.println(p);
        }
    }

    @Test
    void e_delete() {
        ResponseEntity<Void> deleteResponse = restTemplate.exchange(BASE_URL + "/delete/" + productId, HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());
        ResponseEntity<Product> response = restTemplate.getForEntity(BASE_URL + "/read/" + productId, Product.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        System.out.println("Deleted product with ID: " + productId);
    }
}
