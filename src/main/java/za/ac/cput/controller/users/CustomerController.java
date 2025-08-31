package za.ac.cput.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.service.users.CustomerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
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
    public Customer read(@PathVariable Long customerId) {
        return service.read(customerId);
    }

    @PutMapping("/update")
    public Customer update(@RequestBody Customer customer) {
        return service.update(customer);
    }

    @DeleteMapping("/delete/{customerId}")
    public boolean delete(@PathVariable Long customerId) {
        return service.delete(customerId);
    }

    @GetMapping("/getAll")
        public List<Customer> getAll() {
        return service.getAll();
        }


}
