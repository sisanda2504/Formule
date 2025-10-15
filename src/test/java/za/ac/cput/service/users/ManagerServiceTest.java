package za.ac.cput.service.users;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.users.Manager;
import za.ac.cput.factory.users.ManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ManagerServiceTest {

    @Autowired
    private IManagerService managerService;

    private static Manager manager = ManagerFactory.createManager(
            "Thabo",
            "Mokone",
            "0821234567",
            "thabo.manager@gmail.com",
            "manager2025"
    );

    @Test
    @Order(1)
    void a_createManager() {
        Manager created = managerService.create(manager);
        assertNotNull(created);
        assertNotEquals("manager2025", created.getPassword(), "Password should be encoded");
        manager = created;
        System.out.println("Created manager: " + created);
    }

    @Test
    @Order(2)
    void b_readManager() {
        Manager read = managerService.read(manager.getId());
        assertNotNull(read);
        System.out.println("Read manager: " + read);
    }

    @Test
    @Order(3)
    void c_updateManager() {
        Manager updated = new Manager.Builder()
                .copy(manager)
                .setEmailAddress("thabo.updated@gmail.com")
                .build();

        Manager result = managerService.update(updated);
        assertNotNull(result);
        manager = result;
        System.out.println("Updated manager email: " + result);
    }

    @Test
    @Order(4)
    void d_getAllManagers() {
        assertFalse(managerService.getAll().isEmpty());
        System.out.println("All managers: " + managerService.getAll());
    }

    @Test
    @Order(5)
    void e_deleteManager() {
        boolean deleted = managerService.delete(manager.getId());
        assertTrue(deleted);
        System.out.println("Deleted manager: " + deleted);
    }
}