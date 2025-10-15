package za.ac.cput.factory.users;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.users.Admin;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AdminFactoryTest {

    private static Admin admin1 = AdminFactory.createAdmin(
            "Admin",
            "Admin12",
            "admin12a@gmail.com",
            "admin120");

    private static Admin admin2 = AdminFactory.createAdmin(
            "Admin",
            "",
            "admin13a@gmail.com",
            "admin130");

    @Test
    @Order(1)
    public void testCreateAdmin() {
        assertNotNull(admin1);
        assertEquals("ROLE_ADMIN", admin1.getRole()); 
        System.out.println(admin1.toString());
    }

    @Test
    @Order(2)
    public void testCreateAdmin2() {
        assertNull(admin2); 
        System.out.println("admin2 is null as expected due to invalid input.");
    }
}