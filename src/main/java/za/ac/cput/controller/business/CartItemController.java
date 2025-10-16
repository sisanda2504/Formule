package za.ac.cput.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.business.CartItems;
import za.ac.cput.security.AppUserDetails;
import za.ac.cput.service.business.CartItemService;

import java.util.List;

@RestController
@RequestMapping("/formule/cart-items")
@CrossOrigin(origins = "*")
public class CartItemController {

    private final CartItemService service;

    @Autowired
    public CartItemController(CartItemService service) {
        this.service = service;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<CartItems> create(@RequestBody CartItems cartItem, Authentication authentication) {
        AppUserDetails user = (AppUserDetails) authentication.getPrincipal();
        if (!user.getId().equals(cartItem.getCart().getCustomerId())) return ResponseEntity.status(403).build();
        CartItems created = service.create(cartItem);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/read/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<CartItems> read(@PathVariable Long id, Authentication authentication) {
        CartItems found = service.read(id);
        if (found == null) return ResponseEntity.notFound().build();

        AppUserDetails user = (AppUserDetails) authentication.getPrincipal();
        Long ownerId = found.getCart().getCustomerId();
        if (!user.getId().equals(ownerId) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(found);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<CartItems> update(@RequestBody CartItems cartItem, Authentication authentication) {
        AppUserDetails user = (AppUserDetails) authentication.getPrincipal();
        Long ownerId = cartItem.getCart().getCustomerId();
        if (!user.getId().equals(ownerId) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
            return ResponseEntity.status(403).build();
        }
        CartItems updated = service.update(cartItem);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication authentication) {
        CartItems found = service.read(id);
        if (found == null) return ResponseEntity.notFound().build();

        AppUserDetails user = (AppUserDetails) authentication.getPrincipal();
        Long ownerId = found.getCart().getCustomerId();
        if (!user.getId().equals(ownerId) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
            return ResponseEntity.status(403).build();
        }

        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findByCartId/{cartId}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<List<CartItems>> findByCartId(@PathVariable Long cartId, Authentication authentication) {
        // Assuming service can fetch CartItems by Cart ID
        List<CartItems> items = service.findByCartId(cartId);
        if (items.isEmpty()) return ResponseEntity.noContent().build();

        AppUserDetails user = (AppUserDetails) authentication.getPrincipal();
        Long ownerId = items.get(0).getCart().getCustomerId(); // all items belong to same cart
        if (!user.getId().equals(ownerId) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) &&
            !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(items);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<List<CartItems>> getAll() {
        List<CartItems> allItems = service.getAll();
        return ResponseEntity.ok(allItems);
    }
}