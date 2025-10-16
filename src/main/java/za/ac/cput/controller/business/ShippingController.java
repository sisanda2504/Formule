package za.ac.cput.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.business.Shipping;
import za.ac.cput.service.business.ShippingService;

import java.util.List;

@RestController
@RequestMapping("/formule/shipping")
@CrossOrigin(origins = "*")
public class ShippingController {

    private final ShippingService service;

    @Autowired
    public ShippingController(ShippingService service) {
        this.service = service;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public Shipping create(@RequestBody Shipping shipping) {
        return service.create(shipping);
    }

    @GetMapping("/read/{shippingId}")
    public Shipping read(@PathVariable Long shippingId) {
        return service.read(shippingId);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public Shipping update(@RequestBody Shipping shipping) {
        return service.update(shipping);
    }

    @DeleteMapping("/delete/{shippingId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public boolean delete(@PathVariable Long shippingId) {
        return service.delete(shippingId);
    }

    @GetMapping("/getall")
    public List<Shipping> getAll() {
        return service.getAll();
    }
}