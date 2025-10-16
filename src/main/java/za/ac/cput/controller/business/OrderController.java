package za.ac.cput.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.business.Order;
import za.ac.cput.security.AppUserDetails;
import za.ac.cput.service.business.OrderService;

import java.util.List;

@RestController
@RequestMapping("/formule/order")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Order> create(@RequestBody Order order, Authentication authentication) {
        AppUserDetails user = (AppUserDetails) authentication.getPrincipal();
        if (!user.getId().equals(order.getCustomerId())) return ResponseEntity.status(403).build();
        Order created = service.create(order);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/read/{orderId}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Order> read(@PathVariable Long orderId, Authentication authentication) {
        Order order = service.read(orderId);
        if (order == null) return ResponseEntity.notFound().build();

        AppUserDetails user = (AppUserDetails) authentication.getPrincipal();
        if (!user.getId().equals(order.getCustomerId()) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(order);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Order> update(@RequestBody Order order, Authentication authentication) {
        AppUserDetails user = (AppUserDetails) authentication.getPrincipal();
        if (!user.getId().equals(order.getCustomerId()) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
            return ResponseEntity.status(403).build();
        }
        Order updated = service.update(order);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{orderId}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Void> delete(@PathVariable Long orderId, Authentication authentication) {
        Order order = service.read(orderId);
        if (order == null) return ResponseEntity.notFound().build();

        AppUserDetails user = (AppUserDetails) authentication.getPrincipal();
        if (!user.getId().equals(order.getCustomerId()) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
            return ResponseEntity.status(403).build();
        }

        service.delete(orderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getall")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<List<Order>> getAll() {
        List<Order> orders = service.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}