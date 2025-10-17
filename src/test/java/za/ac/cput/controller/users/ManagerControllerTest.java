package za.ac.cput.controller.users;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.users.Manager;
import za.ac.cput.factory.users.ManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ManagerControllerTest {

    private static Manager manager;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/formule/manager";

    @BeforeAll
    static void setUp() {
        manager = ManagerFactory.createManager(
                "Lerato",
                "Nkosi",
                "0796547866",
                "lerato.nkosi@formule.com",
                "manager123"
        );
    }

    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Manager> response = restTemplate.postForEntity(url, manager, Manager.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        manager = response.getBody();
        assertNotNull(manager.getId());
        System.out.println("✅ Created: " + manager);
    }

    @Test
    @Order(2)
    void read() {
        assertNotNull(manager.getId(), "Manager must exist before read test");
        String url = BASE_URL + "/read/" + manager.getId();

        ResponseEntity<Manager> response = restTemplate.getForEntity(url, Manager.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        System.out.println("📦 Read: " + response.getBody());
    }

    @Test
    @Order(3)
    void update() {
        Manager updatedManager = new Manager.Builder()
                .copy(manager)
                .setLastName("Molefe")
                .build();

        String url = BASE_URL + "/update";
        HttpEntity<Manager> request = new HttpEntity<>(updatedManager);
        ResponseEntity<Manager> response = restTemplate.exchange(url, HttpMethod.PUT, request, Manager.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Molefe", response.getBody().getLastName());
        System.out.println("🔁 Updated: " + response.getBody());
    }

    @Test
    @Order(4)
    void getAll() {
        String url = BASE_URL + "/getAll";
        ResponseEntity<Manager[]> response = restTemplate.getForEntity(url, Manager[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);

        System.out.println("📃 All Managers:");
        for (Manager m : response.getBody()) {
            System.out.println(m);
        }
    }

    @Test
    @Order(5)
    void delete() {
        String url = BASE_URL + "/delete/" + manager.getId();
        restTemplate.delete(url);

        ResponseEntity<Manager> response = restTemplate.getForEntity(BASE_URL + "/read/" + manager.getId(), Manager.class);
        assertTrue(response.getStatusCode().is4xxClientError());
        System.out.println("🗑️ Deleted: Manager ID " + manager.getId());
    }
}