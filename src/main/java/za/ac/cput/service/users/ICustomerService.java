package za.ac.cput.service.users;

import za.ac.cput.domain.generic.Address;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.service.IService;

import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IService<Customer, Long> {
    List<Customer> getAll();
    Optional<Customer> login(String email, String password);
}
