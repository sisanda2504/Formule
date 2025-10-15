package za.ac.cput.factory.users;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.users.Manager;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ManagerFactoryTest {

    private static Manager validManager = ManagerFactory.createManager(
            "John",
            "Doe",
            "0723456789",
            "john.doe@example.com",
            "ManagerPass123!"
    );

    @Test
    @Order(1)
    public void testCreateManager() {
        assertNotNull(validManager);
        assertEquals("ROLE_MANAGER", validManager.getRole()); // verify default role
        System.out.println("✅ Manager created: " + validManager);
    }

    @Test
    @Order(2)
    void testCreateManagerWithInvalidEmail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                ManagerFactory.createManager(
                        "John",
                        "Doe",
                        "0723456789",
                        "invalidEmail",
                        "ManagerPass123!"
                )
        );
        System.out.println("Expected exception for invalid email: " + exception.getMessage());
    }

    @Test
    @Order(3)
    void testCreateManagerWithEmptyLastName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                ManagerFactory.createManager(
                        "John",
                        "",
                        "0723456789",
                        "john.doe@example.com",
                        "ManagerPass123!"
                )
        );
        System.out.println("Expected exception for empty last name: " + exception.getMessage());
    }

    @Test
    @Order(4)
    void testCreateManagerWithInvalidPhoneNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                ManagerFactory.createManager(
                        "John",
                        "Doe",
                        "123",
                        "john.doe@example.com",
                        "ManagerPass123!"
                )
        );
        System.out.println("Expected exception for invalid phone number: " + exception.getMessage());
    }
}