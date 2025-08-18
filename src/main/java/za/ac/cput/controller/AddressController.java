package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Address;
import za.ac.cput.service.IAddressService;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final IAddressService service;

    @Autowired
    public AddressController(IAddressService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Address create(@RequestBody Address address) {
        return service.create(address);
    }

    @GetMapping("/read/{id}")
    public Address read(@PathVariable Integer id) {
        return service.read(id);
    }

    @PutMapping("/update")
    public Address update(@RequestBody Address address) {
        return service.update(address);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @GetMapping("/getall")
    public List<Address> getAll() {
        return service.getAll();
    }

    @GetMapping("/customer/{customerId}")
    public List<Address> getByCustomerId(@PathVariable int customerId) {
        return service.findByCustomerId(customerId);
    }
}
