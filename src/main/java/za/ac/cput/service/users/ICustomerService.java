package za.ac.cput.service.users;

import za.ac.cput.domain.users.Customer;
import za.ac.cput.service.IService;

import java.util.List;

public interface
ICustomerService extends IService<Customer, Integer> {

    boolean deleteCustomer(Integer id);
    List<Customer> getAll();
}
