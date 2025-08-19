package za.ac.cput.factory.generic;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.generic.Address;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressFactoryTest {

    private static Address address1 = AddressFactory.createAddress(
            1,
            109,
            "123 Main Street",
            "Cape Town",
            "Western Cape",
            "8001",
            "South Africa"
    );

    @Test
    @Order(1)
    void testCreateAddressSuccess() {
        assertNotNull(address1);
        System.out.println(address1.toString());
    }

    @Test
    @Order(2)
    void testCreateAddressWithEmptyStreet() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AddressFactory.createAddress(
                    2,
                    110,
                    "",
                    "Durban",
                    "KwaZulu-Natal",
                    "4001",
                    "South Africa"
            );
        });
        assertEquals("Please provide a street", exception.getMessage());
        System.out.println("Exception caught: " + exception.getMessage());
    }

    @Test
    @Order(3)
    void testCreateAddressWithNullCity() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AddressFactory.createAddress(
                    3,
                    111,
                    "456 Oak Avenue",
                    null,
                    "Gauteng",
                    "2000",
                    "South Africa"
            );
        });
        assertEquals("Please provide a city", exception.getMessage());
        System.out.println("Exception caught: " + exception.getMessage());
    }
}
