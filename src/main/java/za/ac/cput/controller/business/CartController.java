package za.ac.cput.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.business.Cart;
import org.springframework.web.bind.annotation.CrossOrigin;
import za.ac.cput.service.business.CartService;

import java.util.List;

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
}
