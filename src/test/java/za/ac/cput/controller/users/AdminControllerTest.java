package za.ac.cput.controller.users;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.users.Admin;
import za.ac.cput.factory.users.AdminFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AdminControllerTest {

    private static Admin admin;

    @Autowired
    private TestRestTemplate restTemplate;

    // ✅ Keeping your preferred explicit URL
    private final String BASE_URL = "http://localhost:8080/formule/admin";

    @BeforeAll
    static void setUp() {
        admin = AdminFactory.createAdmin(
                "Agnes",
                "Mabusela",
                "Agnes@admin.com",
                "password@01"
        );
    }

    @Test
    @Order(1)
    void a_create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Admin> response = restTemplate.postForEntity(url, admin, Admin.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        admin = response.getBody();
        System.out.println("✅ Created: " + admin);
    }

    @Test
    @Order(2)
    void b_read() {
        String url = BASE_URL + "/read/" + admin.getId();
        ResponseEntity<Admin> response = restTemplate.getForEntity(url, Admin.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("✅ Read: " + response.getBody());
    }

    @Test
    @Order(3)
    void c_update() {
        Admin updated = new Admin.Builder()
                .copy(admin)
                .setFirstName("UpdatedName")
                .build();

        String url = BASE_URL + "/update";
        ResponseEntity<Admin> response = restTemplate.postForEntity(url, updated, Admin.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("UpdatedName", response.getBody().getFirstName());
        admin = response.getBody();
        System.out.println("✅ Updated: " + response.getBody());
    }

    @Test
    @Order(4)
    void d_getAll() {
        String url = BASE_URL + "/getAll";
        ResponseEntity<Admin[]> response = restTemplate.getForEntity(url, Admin[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("✅ All Admins:");
        for (Admin a : response.getBody()) {
            System.out.println(a);
        }
    }

    @Test
    @Order(5)
    void e_delete() {
        String url = BASE_URL + "/delete/" + admin.getId();
        restTemplate.delete(url);
        System.out.println("✅ Deleted admin with ID: " + admin.getId());
    }
}