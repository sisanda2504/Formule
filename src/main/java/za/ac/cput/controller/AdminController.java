package za.ac.cput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Customer;
import za.ac.cput.service.AdminService;
import za.ac.cput.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService service;

    @Autowired
    public AdminController(AdminService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Admin create(@RequestBody Admin admin) {
        return service.create(admin);
    }

    @GetMapping("/read/{adminId}")
    public Admin read(@PathVariable Integer adminId) {
        return service.read(adminId);
    }

    @GetMapping("/update")
    public Admin update(@RequestBody Admin admin) {
        return service.update(admin);
    }

    @DeleteMapping("/delete/{adminId}")
    public boolean delete(@PathVariable Integer adminId) {
        return service.deleteAdmin(adminId);
    }

    @GetMapping("/getAll")
    public List<Admin> getAll() {
        return service.getAll();
    }

}
