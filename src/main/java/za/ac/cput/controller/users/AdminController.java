package za.ac.cput.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.users.Admin;
import za.ac.cput.service.users.IAdminService;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final IAdminService service;

    @Autowired
    public AdminController(IAdminService service) {
        this.service = service;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can create another admin
    public ResponseEntity<Admin> create(@RequestBody Admin admin) {
        Admin created = service.create(admin);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/read/{adminId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Admin> read(@PathVariable Long adminId) {
        Admin admin = service.read(adminId);
        if (admin != null) return ResponseEntity.ok(admin);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Admin> update(@RequestBody Admin admin) {
        Admin updated = service.update(admin);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{adminId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> delete(@PathVariable Long adminId) {
        boolean deleted = service.delete(adminId);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Admin>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

}