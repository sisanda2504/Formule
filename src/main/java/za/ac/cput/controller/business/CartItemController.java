package za.ac.cput.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.business.CartItems;
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
    public ResponseEntity<CartItems> create(@RequestBody CartItems cartItem) {
        CartItems created = service.create(cartItem);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<CartItems> read(@PathVariable Long id) {
        CartItems found = service.read(id);
        if (found == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(found);
    }

    @PostMapping("/update")
    public ResponseEntity<CartItems> update(@RequestBody CartItems cartItem) {
        CartItems updated = service.update(cartItem);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean success = service.delete(id);
        if (success)
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findByCartId/{cartId}")
    public ResponseEntity<List<CartItems>> findByCartId(@PathVariable Long cartId) {
        List<CartItems> items = service.findByCartId(cartId);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CartItems>> getAll() {
        List<CartItems> allItems = service.getAll();
        return ResponseEntity.ok(allItems);
    }
}
