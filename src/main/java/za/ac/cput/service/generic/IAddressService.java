package za.ac.cput.service.generic;

import za.ac.cput.domain.generic.Address;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.service.IService;

import java.util.List;

public interface IAddressService extends IService<Address, Integer> {

    List<Address> getAll();
    List<Address> findByCustomerId(Long customerId);
}
