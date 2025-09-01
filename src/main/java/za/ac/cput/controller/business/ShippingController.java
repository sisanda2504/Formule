/*ShippingController.java
Author: Tsholofelo Mabidikane (230018165)
Date: 07 August 2025
 */
package za.ac.cput.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.service.business.ShippingService;
import za.ac.cput.domain.business.Shipping;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/shipping")
@CrossOrigin(origins = "*")
public class ShippingController {

    private ShippingService service;

    @Autowired
    public ShippingController(ShippingService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Shipping create(@RequestBody Shipping shipping) {
        return service.create(shipping);
    }

    @GetMapping("/read/{shippingId}")
    public Shipping read(@PathVariable Long shippingId) {
        return service.read(shippingId);
    }

    @PutMapping("/update")
    public Shipping update(@RequestBody Shipping shipping) {
        return service.update(shipping);
    }

    @DeleteMapping("/delete/{shippingId}")
    public boolean delete(@PathVariable Long shippingId) {
        return service.delete(shippingId);
    }

    @GetMapping("/getall")
    public List<Shipping> getAll() {
        return service.getAll();
    }
}
