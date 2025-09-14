package za.ac.cput.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.business.Cart;
import za.ac.cput.domain.business.CartItems;
import za.ac.cput.service.business.CartService;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:5173/")
public class CartController {

    private final CartService service;

    @Autowired
    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Cart create(@RequestBody Cart cart) {
        return service.create(cart);
    }

    @GetMapping("/read/{id}")
    public Cart read(@PathVariable Long id) {
        return service.read(id);
    }

    @PutMapping("/update")
    public Cart update(@RequestBody Cart cart) {
        return service.update(cart);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/getall")
    public List<Cart> getAll() {
        return service.getAll();
    }

    @GetMapping("/findByCustomerId/{customerId}")
    public Cart findByCustomerId(@PathVariable Long customerId) {
        return service.findByCustomerId(customerId);
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