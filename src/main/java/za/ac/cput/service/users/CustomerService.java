package za.ac.cput.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.repository.users.CustomerRepository;

import java.util.List;

@Service
public class CustomerService  implements ICustomerService {

    @Autowired
    private static CustomerService service;
    private CustomerRepository repository;

    @Override
    public Customer create(Customer customer) {
        return this.repository.save(customer);
    }

    @Override
    public Customer read(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public Customer update(Customer customer) {
        return this.repository.save(customer);
    }

    @Override
    public boolean deleteCustomer(Integer id) {
        this.repository.deleteById(id);
        return true;
    }

    @Override
    public List<Customer> getAll() {
        return this.repository.findAll();
    }

}
