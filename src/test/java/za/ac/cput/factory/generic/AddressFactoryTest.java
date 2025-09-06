package za.ac.cput.factory.generic;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.generic.Address;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.users.CustomerFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressFactoryTest {

    private static final Customer customer = CustomerFactory.createCustomer(
            "Test",
            "User",
            "0712345678",
            "testuser@example.com",
            "Password123!"
    );

    @Test
    @Order(1)
    void testCreateAddressSuccess() {
        Address address = AddressFactory.createAddress(
                customer,
                "123 Main Street",
                "Cape Town",
                "Western Cape",
                "8001",
                "South Africa"
        );
        assertNotNull(address);
        System.out.println("✅ Address created: " + address);
    }

    @Test
    @Order(2)
    void testCreateAddressWithEmptyStreet() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                AddressFactory.createAddress(
                        customer,
                        "",
                        "Durban",
                        "KwaZulu-Natal",
                        "4001",
                        "South Africa"
                )
        );
        System.out.println("❌ Expected exception for empty street: " + exception.getMessage());
    }

    @Test
    @Order(3)
    void testCreateAddressWithNullCity() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                AddressFactory.createAddress(
                        customer,
                        "456 Oak Avenue",
                        null,
                        "Gauteng",
                        "2000",
                        "South Africa"
                )
        );
        System.out.println("❌ Expected exception for null city: " + exception.getMessage());
    }

    @Test
    @Order(4)
    void testCreateAddressWithNullCustomer() {
        Address address = AddressFactory.createAddress(
                null,
                "78 Palm Grove",
                "Johannesburg",
                "Gauteng",
                "2190",
                "South Africa"
        );
        assertNotNull(address);
        System.out.println("✅ Address with null customer: " + address);
    }
}
