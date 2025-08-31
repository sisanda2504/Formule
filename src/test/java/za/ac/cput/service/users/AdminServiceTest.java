package za.ac.cput.service.users;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.users.Admin;
import za.ac.cput.factory.users.AdminFactory;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class AdminServiceTest {
    @Autowired

    private  IAdminService service;
    private static Admin admin = AdminFactory.createAdmin(
            "Agnes",
            "Mabusela",
            "agnesadmin@gmail.com",
            "admin2025");

    @Test
    void a_create() {
        Admin created = service.create(admin);
        assertNotNull(created);
        admin = created;
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        Admin read = service.read(admin.getId());
        assertNotNull(read);
        System.out.println("Read: " + read);
    }

    @Test
    void c_update() {
        Admin newAdmin = new Admin.Builder()
                .copy(admin)
                .setEmailAddress("agnesmabuselaAdmin@gmail.com")
                .build();
        Admin updated = service.update(newAdmin);
        assertNotNull(updated);
        admin = updated;
        System.out.println("Updated: " + updated);
    }

    @Test
    void e_delete() {
        boolean deleted = service.delete(admin.getId());
        assertTrue(deleted);
        System.out.println("Deleted: "+ deleted);
    }

    @Test
    void d_getAll() {
        System.out.println("All admins: " + service.getAll());
    }
}