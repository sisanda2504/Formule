package za.ac.cput.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.business.Cart;
import za.ac.cput.domain.business.CartItems;
import za.ac.cput.security.AppUserDetails;
import za.ac.cput.service.business.CartService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formule/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService service;

    @Autowired
    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Cart> create(@RequestBody Cart cart, Authentication authentication) {
        AppUserDetails user = (AppUserDetails) authentication.getPrincipal();
        if (!user.getId().equals(cart.getCustomerId())) return ResponseEntity.status(403).build();
        Cart created = service.create(cart);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/read/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Cart> read(@PathVariable Long id, Authentication authentication) {
        Cart cart = service.read(id);
        if (cart == null) return ResponseEntity.notFound().build();

        AppUserDetails user = (AppUserDetails) authentication.getPrincipal();
        if (!user.getId().equals(cart.getCustomerId()) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Cart> update(@RequestBody Cart cart, Authentication authentication) {
        AppUserDetails user = (AppUserDetails) authentication.getPrincipal();
        if (!user.getId().equals(cart.getCustomerId()) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
            return ResponseEntity.status(403).build();
        }
        Cart updated = service.update(cart);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication authentication) {
        Cart cart = service.read(id);
        if (cart == null) return ResponseEntity.notFound().build();

        AppUserDetails user = (AppUserDetails) authentication.getPrincipal();
        if (!user.getId().equals(cart.getCustomerId()) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
            return ResponseEntity.status(403).build();
        }

        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getall")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<List<Cart>> getAll() {
        List<Cart> carts = service.getAll();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/findByCustomerId/{customerId}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Cart> findByCustomerId(@PathVariable Long customerId, Authentication authentication) {
        AppUserDetails user = (AppUserDetails) authentication.getPrincipal();
        if (!user.getId().equals(customerId) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
            return ResponseEntity.status(403).build();
        }

        Optional<Cart> cart = service.findByCustomerId(customerId);
        return cart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<CartItems> addToCart(
            @RequestParam Long productId,
            @RequestParam(defaultValue = "1") int quantity,
            Authentication authentication) {

        AppUserDetails user = (AppUserDetails) authentication.getPrincipal();
        try {
            CartItems item = service.addToCart(user.getId(), productId, quantity);
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/remove")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<String> removeFromCart(
            @RequestParam Long productId,
            Authentication authentication) {

        AppUserDetails user = (AppUserDetails) authentication.getPrincipal();
        try {
            boolean removed = service.removeFromCart(user.getId(), productId);
            if (removed) return ResponseEntity.ok("Item removed successfully from cart.");
            else return ResponseEntity.badRequest().body("Item not found in cart.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to remove item: " + e.getMessage());
        }
    }
}