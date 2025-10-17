package za.ac.cput.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.dto.users.LoginResponse;
import za.ac.cput.security.AppUserDetails;
import za.ac.cput.service.users.ICustomerService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/formule/customer")
public class CustomerController {

    private final ICustomerService service;

    @Autowired
    public CustomerController(ICustomerService service) {
        this.service = service;
    }

    private LoginResponse toDto(Customer c) {
        return new LoginResponse(
            c.getId(),
            c.getFirstName(),
            c.getLastName(),
            c.getEmailAddress(),
            c.getRole()
        );
    }

    @PostMapping("/create")
    public ResponseEntity<LoginResponse> create(@RequestBody Customer customer) {
        Customer created = service.create(customer);
        return ResponseEntity.ok(toDto(created));
    }

    @GetMapping("/read/{customerId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<LoginResponse> read(@PathVariable Long customerId) {
        Customer customer = service.read(customerId);
        if (customer != null) return ResponseEntity.ok(toDto(customer));
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<LoginResponse> update(@RequestBody Customer customer, Authentication authentication) {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        if (!userDetails.getId().equals(customer.getId()) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return ResponseEntity.status(403).build();
        }
        Customer updated = service.update(customer);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/delete/{customerId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> delete(@PathVariable Long customerId, Authentication authentication) {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        if (!userDetails.getId().equals(customerId) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return ResponseEntity.status(403).build();
        }
        boolean deleted = service.delete(customerId);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<LoginResponse>> getAll() {
        List<Customer> customers = service.getAll();
        List<LoginResponse> dtos = customers.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}