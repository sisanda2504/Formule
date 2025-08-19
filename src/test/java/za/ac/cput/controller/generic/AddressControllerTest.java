package za.ac.cput.controller.generic;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import za.ac.cput.domain.generic.Address;
import za.ac.cput.service.generic.IAddressService;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressControllerTest {
    private static IAddressService service;
    private static AddressController controller;
    private static Address address;

    @BeforeAll
    static void setUp() {
        service = Mockito.mock(IAddressService.class);
        controller = new AddressController(service);
        address = new Address.Builder()
                .setId(1)
                .setCustomerId(100)
                .setStreet("123 Main Street")
                .setCity("Cape Town")
                .setProvince("Western Cape")
                .setPostalCode("8001")
                .setCountry("South Africa")
                .build();
    }
    @Test
    @Order(1)
    void testCreate() {
        when(service.create(any(Address.class))).thenReturn(address);
        Address created = controller.create(address);
        assertNotNull(created);
        assertEquals("Cape Town", created.getCity());
        verify(service, times(1)).create(address);
        System.out.println("Created: " + created);
    }
    @Test
    @Order(2)
    void testRead() {
        when(service.read(1)).thenReturn(address);
        Address found = controller.read(1);
        assertNotNull(found);
        assertEquals(1, found.getId());
        verify(service, times(1)).read(1);
        System.out.println("Read: " + found);
    }
    @Test
    @Order(3)
    void testUpdate() {
        Address updatedAddress = new Address.Builder().copy(address).setCity("Johannesburg").build();
        when(service.update(any(Address.class))).thenReturn(updatedAddress);
        Address updated = controller.update(updatedAddress);
        assertNotNull(updated);
        assertEquals("Johannesburg", updated.getCity());
        verify(service, times(1)).update(updatedAddress);
        System.out.println("Updated: " + updated);
    }
    @Test
    @Order(4)
    void testDelete() {
        when(service.delete(1)).thenReturn(true);
        boolean result = controller.delete(1);
        assertTrue(result);
        verify(service, times(1)).delete(1);
        System.out.println("Deleted address with ID 1");
    }
    @Test
    @Order(5)
    void testGetAll() {
        when(service.getAll()).thenReturn(Arrays.asList(address));
        List<Address> all = controller.getAll();
        assertFalse(all.isEmpty());
        assertEquals(1, all.size());
        verify(service, times(1)).getAll();
        System.out.println("All addresses: " + all);
    }
    @Test
    @Order(6)
    void testGetByCustomerId() {
        when(service.findByCustomerId(100)).thenReturn(Arrays.asList(address));
        List<Address> customerAddresses = controller.getByCustomerId(100);
        assertFalse(customerAddresses.isEmpty());
        assertEquals(100, customerAddresses.get(0).getCustomerId());
        verify(service, times(1)).findByCustomerId(100);
        System.out.println("Addresses by customer ID 100: " + customerAddresses);
    }
}

