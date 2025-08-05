package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Admin;
import za.ac.cput.factory.AdminFactory;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class AdminServiceTest {
    @Autowired

    private  static IAdminService service;
    private static Admin admin = AdminFactory.createAdmin(
            "Agnes",
            "Mabusela",
            "agnesadmin@gmail.com",
            "admin2025");

    @Test
    void a_create() {
        Admin created = service.create(admin);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void b_read() {
        Admin read = service.read(admin.getId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        Admin newAdmin = new Admin.Builder().copy(admin).setEmailAddress("agnesmabuselaAdmin@gmail.com").build();
        Admin updated = service.update(admin);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void e_deleteAdmin() {
    }

    @Test
    void d_getAll() {
        System.out.println(service.getAll());
    }
}