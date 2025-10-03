package za.ac.cput.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.business.Cart;
import za.ac.cput.domain.business.CartItems;
import za.ac.cput.service.business.CartService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService service;

    @Autowired
    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Cart> create(@RequestBody Cart cart) {
        Cart created = service.create(cart);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Cart> read(@PathVariable Long id) {
        Cart cart = service.read(id);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/update")
    public ResponseEntity<Cart> update(@RequestBody Cart cart) {
        Cart updated = service.update(cart);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Cart>> getAll() {
        List<Cart> carts = service.getAll();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/findByCustomerId/{customerId}")
    public ResponseEntity<Cart> findByCustomerId(@PathVariable Long customerId) {
        Optional<Cart> cart = service.findByCustomerId(customerId);
        if (cart.isPresent()) {
            return ResponseEntity.ok(cart.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/add")
    public ResponseEntity<CartItems> addToCart(
            @RequestParam Long customerId,
            @RequestParam Long productId,
            @RequestParam(defaultValue = "1") int quantity) {
        try {
            CartItems item = service.addToCart(customerId, productId, quantity);
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFromCart(
            @RequestParam Long customerId,
            @RequestParam Long productId) {
        try {
            boolean removed = service.removeFromCart(customerId, productId);
            if (removed) {
                return ResponseEntity.ok("Item removed successfully from cart.");
            } else {
                return ResponseEntity.badRequest().body("Item not found in cart.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to remove item: " + e.getMessage());
        }
    }
}
