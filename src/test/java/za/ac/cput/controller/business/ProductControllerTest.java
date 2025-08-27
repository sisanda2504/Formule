package za.ac.cput.controller.business;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.business.Brands;
import za.ac.cput.domain.business.Product;
import za.ac.cput.factory.business.ProductFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class ProductControllerTest {

    private static Product product;

    @Autowired
    private TestRestTemplate restTemplate;
    private static final String BASE_URL = "http://localhost:8080/formule/product";

    @BeforeAll
    static void setUp() {
        product = ProductFactory.createProduct(
                1,
                "Test Product",
                "Test Description",
                99.99,
                100,
                1,
                Brands.INNISFREE
        );
        assertNotNull(product);
    }

    @Test
    void a_create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Product> postResponse = restTemplate.postForEntity(url, product, Product.class);
        assertNotNull(postResponse);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        Product productSaved = postResponse.getBody();
        assertNotNull(productSaved);
        assertEquals(product.getId(), productSaved.getId());
        assertEquals(product.getName(), productSaved.getName());
        assertEquals(product.getPrice(), productSaved.getPrice());
        assertEquals(product.getQuantity(), productSaved.getQuantity());
        assertEquals(product.getCategoryId(), productSaved.getCategoryId());
        assertEquals(product.getBrand(), productSaved.getBrand());
        System.out.println("Created: " + productSaved);
    }

    @Test
    void b_read() {
        String url = BASE_URL + "/read/" + product.getId();
        ResponseEntity<Product> response = restTemplate.getForEntity(url, Product.class);
        assertNotNull(response.getBody());
        assertEquals(product.getId(), response.getBody().getId());
        assertEquals(product.getName(), response.getBody().getName());
        assertEquals(product.getBrand(), response.getBody().getBrand());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    void c_update() {
        Product updatedProduct = ProductFactory.createProduct(
                product.getId(),
                "Updated Product",
                "Updated Description",
                199.99,
                200,
                2,
                Brands.MISSHA
        );
        String url = BASE_URL + "/update";
        ResponseEntity<Product> response = restTemplate.postForEntity(url, updatedProduct, Product.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedProduct.getName(), response.getBody().getName());
        assertEquals(updatedProduct.getPrice(), response.getBody().getPrice());
        assertEquals(updatedProduct.getQuantity(), response.getBody().getQuantity());
        assertEquals(updatedProduct.getCategoryId(), response.getBody().getCategoryId());
        assertEquals(updatedProduct.getBrand(), response.getBody().getBrand());
        System.out.println("Updated: " + response.getBody());
    }

    @Test
    void d_delete() {
        String url = BASE_URL + "/delete/" + product.getId();
        restTemplate.delete(url);

        ResponseEntity<Product> response = restTemplate.getForEntity(BASE_URL + "/read/" + product.getId(), Product.class);
        assertNull(response.getBody());
        System.out.println("Deleted: " + response.getBody());
    }

    @Test
    void e_getAll() {
        String url = BASE_URL + "/getAll";
        ResponseEntity<Product[]> response = restTemplate.getForEntity(url, Product[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        System.out.println("GetAll: ");
        for (Product p : response.getBody()) {
            System.out.println(p);
        }
    }
}