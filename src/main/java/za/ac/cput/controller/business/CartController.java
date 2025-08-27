package za.ac.cput.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.business.Cart;
import za.ac.cput.service.business.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
    private CartService service;

    @Autowired
    public CartController(CartService service) {
        this.service = service;}
    @PostMapping("/create")
    public Cart create(@RequestBody Cart cart) {

        return service.create(cart);
    }
    @PostMapping("/read")
    public Cart read(@RequestBody Integer id) {
        return service.read(id);
    }
    @PutMapping("/update")
    public Cart update(@RequestBody Cart cart) {

        return service.update(cart);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
    @GetMapping("/getAll")
    public Iterable<Cart> getAll() {
        return service.getAll();
    }
}
