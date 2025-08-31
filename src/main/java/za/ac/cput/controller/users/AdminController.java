package za.ac.cput.controller.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.users.Admin;
import za.ac.cput.service.users.AdminService;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService service;

    @Autowired
    public AdminController(AdminService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Admin create(@RequestBody Admin admin) {
        return service.create(admin);
    }

    @GetMapping("/read/{adminId}")
    public Admin read(@PathVariable Long adminId) {
        return service.read(adminId);
    }

    @PostMapping("/update")
    public Admin update(@RequestBody Admin admin) {
        return service.update(admin);
    }

    @DeleteMapping("/delete/{adminId}")
    public boolean delete(@PathVariable Long adminId) {
        return service.delete(adminId);
    }

    @GetMapping("/getAll")
    public List<Admin> getAll() {
        return service.getAll();
    }

}
