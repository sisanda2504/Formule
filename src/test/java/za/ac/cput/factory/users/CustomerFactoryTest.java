package za.ac.cput.factory.users;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.generic.Address;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.generic.AddressFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerFactoryTest {

    private  static Address validAddress = AddressFactory.createAddress(
            null,
            "123 Saint John Street",
            "Cape Town",
            "Western Cape",
            "8000",
            "South Africa");

    private static Customer validCustomer = CustomerFactory.createCustomer(
            "Agnes",
            "Mabusela",
            "0712345678",
            "agnes@example.com",
            "StrongPass123!",
            validAddress);

    @Test
    @Order(1)
    public void testCreateCustomer() {
        assertNotNull(validCustomer);
        System.out.println("Customer: " + validCustomer);
    }

    @Test
    @Order(2)
    void testCreateCustomerWithInvalidEmail() {
        Customer customer = CustomerFactory.createCustomer(
                "Agnes", "Mabusela", "0712345678", "invalidEmail", "StrongPass123!", validAddress);
        assertNull(customer);
    }

    @Test
    @Order(3)
    void testCreateCustomerWithEmptyFirstName() {
        Customer customer = CustomerFactory.createCustomer(
                "", "Mabusela", "0712345678", "agnes@example.com", "StrongPass123!", validAddress);
        assertNull(customer);
    }


}
