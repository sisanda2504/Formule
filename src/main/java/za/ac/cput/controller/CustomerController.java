package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Customer create(@RequestBody Customer customer) {
        return service.create(customer);
    }

    @GetMapping("/read/{customerId}")
    public Customer read(@PathVariable Integer customerId) {
        return service.read(customerId);
    }

    @GetMapping("/update")
    public Customer update(@RequestBody Customer customer) {
        return service.update(customer);
    }

    @DeleteMapping("/delete/{AddressId}")
    public boolean delete(@PathVariable Integer customerId) {
        return service.deleteCustomer(customerId);
    }

    @GetMapping("/getAll")
        public List<Customer> getAll() {
        return service.getAll();
        }

}
