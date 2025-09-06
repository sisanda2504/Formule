package za.ac.cput.factory.users;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.users.Customer;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerFactoryTest {

    private static Customer validCustomer = CustomerFactory.createCustomer(
            "Agnes",
            "Mabusela",
            "0712345678",
            "agnes@example.com",
            "StrongPass123!"
    );

    @Test
    @Order(1)
    public void testCreateCustomer() {
        assertNotNull(validCustomer);
        System.out.println("✅ Customer created: " + validCustomer);
    }

    @Test
    @Order(2)
    void testCreateCustomerWithInvalidEmail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                CustomerFactory.createCustomer(
                        "Agnes",
                        "Mabusela",
                        "0712345678",
                        "invalidEmail",
                        "StrongPass123!"
                )
        );
        System.out.println("Expected exception for invalid email: " + exception.getMessage());
    }

    @Test
    @Order(3)
    void testCreateCustomerWithEmptyFirstName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                CustomerFactory.createCustomer(
                        "",
                        "Mabusela",
                        "0712345678",
                        "agnes@example.com",
                        "StrongPass123!"
                )
        );
        System.out.println("Expected exception for empty first name: " + exception.getMessage());
    }
}
