package za.ac.cput.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import za.ac.cput.domain.users.Admin;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.domain.users.Manager;
import za.ac.cput.factory.users.AdminFactory;
import za.ac.cput.factory.users.CustomerFactory;
import za.ac.cput.factory.users.ManagerFactory;
import za.ac.cput.service.users.IAdminService;
import za.ac.cput.service.users.ICustomerService;
import za.ac.cput.service.users.IManagerService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppUserDetailsServiceTest {

    @Autowired
    private AppUserDetailsService userDetailsService;

    @Autowired
    private IAdminService adminService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IManagerService managerService;

    private Admin admin;
    private Customer customer;
    private Manager manager;

    @BeforeEach
    void setUp() {
        // Create test users
        admin = adminService.create(AdminFactory.createAdmin(
                "Admin", "One", "0765764378", "admin@test.com","password123"
        ));
        customer = customerService.create(CustomerFactory.createCustomer(
                "Customer", "One", "0712345678", "customer1@test.com", "password123"
        ));
        manager = managerService.create(ManagerFactory.createManager(
                "Manager", "One", "0723456789", "manager1@test.com", "password123"
        ));
    }

    @Test
    void loadAdminByUsername_Success() {
        UserDetails userDetails = userDetailsService.loadUserByUsername(admin.getEmailAddress());
        assertNotNull(userDetails);
        assertEquals(admin.getEmailAddress(), userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals(admin.getRole())));
    }

    @Test
    void loadCustomerByUsername_Success() {
        UserDetails userDetails = userDetailsService.loadUserByUsername(customer.getEmailAddress());
        assertNotNull(userDetails);
        assertEquals(customer.getEmailAddress(), userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals(customer.getRole())));
    }

    @Test
    void loadManagerByUsername_Success() {
        UserDetails userDetails = userDetailsService.loadUserByUsername(manager.getEmailAddress());
        assertNotNull(userDetails);
        assertEquals(manager.getEmailAddress(), userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals(manager.getRole())));
    }

    @Test
    void loadUserByUsername_NotFound() {
        assertThrows(UsernameNotFoundException.class, () -> 
                userDetailsService.loadUserByUsername("nonexistent@test.com"));
    }
}