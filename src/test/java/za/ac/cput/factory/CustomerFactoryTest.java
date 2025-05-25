package za.ac.cput.factory;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Customer;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerFactoryTest {

    private static Customer customer1  = CustomerFactory.createCustomer(
            91,
            "Agnes",
            "Mabusela",
            "0726736282",
            "agnes@gmail.com",
            "pass2025d",
            109);

    private static Customer customer2  = CustomerFactory.createCustomer(
            93,
            "Agnes",
            "Mabusela",
            "0822838292",
            "agnesgmail.com",
            "pass2025b",
            100);


    @Test
    @Order(1)
    public void testCreateCustomer1() {
        assertNotNull(customer1);
        System.out.println(customer1.toString());
    }

    @Test
    @Order(2)
    public void testCreateCustomer2() {
        assertNotNull(customer2);
        System.out.println(customer2.toString());
    }

}