package za.ac.cput.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.generic.Address;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.factory.generic.AddressFactory;
import za.ac.cput.repository.users.CustomerRepository;
import za.ac.cput.service.generic.AddressService;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AddressService addressService;

    @Autowired
    public CustomerService(CustomerRepository repository, AddressService addressService) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.addressService = addressService;
    }

    @Override
    public Customer create(Customer customer) {
        customer = new Customer.Builder()
                .copy(customer)
                .setPassword(passwordEncoder.encode(customer.getPassword()))
                .build();
        return repository.save(customer);
    }

    @Override
    public Customer read(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public Customer update(Customer customer) {
        return this.repository.save(customer);
    }

    @Override
    public boolean delete(Long id) {
        return this.repository.findById(id).map(customer -> {
            this.repository.delete(customer);
            return true;
        }).orElse(false);
    }

    @Override
    public List<Customer> getAll() {
        return this.repository.findAll();
    }

    
}
