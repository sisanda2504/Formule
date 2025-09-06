package za.ac.cput.service.generic;

import za.ac.cput.domain.generic.Address;

import java.util.List;

public interface IAddressService {
    Address create(Address address);
    Address read(int id);
    Address update(Address address);
    boolean delete(int id);
    List<Address> getAll();
    List<Address> findByCustomerId(Long customerId);
}
