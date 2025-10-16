package za.ac.cput.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.users.Admin;
import za.ac.cput.dto.users.LoginResponse;
import za.ac.cput.service.users.IAdminService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final IAdminService service;

    @Autowired
    public AdminController(IAdminService service) {
        this.service = service;
    }

    private LoginResponse toDto(Admin a) {
        return new LoginResponse(
            a.getId(),
            a.getFirstName(),
            a.getLastName(),
            a.getEmailAddress(),
            a.getRole()
        );
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')") // Only admins can create admins
    public ResponseEntity<LoginResponse> create(@RequestBody Admin admin) {
        Admin created = service.create(admin);
        return ResponseEntity.ok(toDto(created));
    }

    @GetMapping("/read/{adminId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LoginResponse> read(@PathVariable Long adminId) {
        Admin admin = service.read(adminId);
        if (admin != null) return ResponseEntity.ok(toDto(admin));
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LoginResponse> update(@RequestBody Admin admin) {
        Admin updated = service.update(admin);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/delete/{adminId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> delete(@PathVariable Long adminId) {
        boolean deleted = service.delete(adminId);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<LoginResponse>> getAll() {
        List<Admin> admins = service.getAll();
        List<LoginResponse> dtos = admins.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}