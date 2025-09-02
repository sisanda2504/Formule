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
            "Password123!",
            null
    );

    private static Address validAddress = AddressFactory.createAddress(
            customer,
            "123 Main Street",
            "Cape Town",
            "Western Cape",
            "8001",
            "South Africa"
    );
    private static Address addressWithNoStreet = AddressFactory.createAddress(
            customer,
            "",
            "Durban",
            "KwaZulu-Natal",
            "4001",
            "South Africa"
    );;
    private static Address addressWithNoCity =  AddressFactory.createAddress(
            customer,
            "456 Oak Avenue",
            null,
            "Gauteng",
            "2000",
            "South Africa"
    );;

    @Test
    @Order(1)
    void testCreateAddressSuccess() {
        assertNotNull(validAddress);
        System.out.println(validAddress.toString());
    }

    @Test
    @Order(2)
    void testCreateAddressWithEmptyStreet() {
        assertNull(addressWithNoStreet);
        System.out.println(addressWithNoStreet.toString());
    }

    @Test
    @Order(3)
    void testCreateAddressWithNullCity() {
        assertNull(addressWithNoCity);
        System.out.println(addressWithNoCity.toString());
    }
}
