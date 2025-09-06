package za.ac.cput.service.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.generic.Address;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.repository.generic.AddressRepository;
import za.ac.cput.repository.users.CustomerRepository;

import java.util.List;

@Service
public class AddressService implements IAddressService {

    private final AddressRepository repository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AddressService(AddressRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Address create(Address address) {
        if (address == null)
            throw new IllegalArgumentException("Address cannot be null");

        if (address.getCustomer() == null || address.getCustomer().getId() == null) {
            throw new IllegalArgumentException("Address must be linked to a valid customer");
        }

        Customer managedCustomer = customerRepository.findById(address.getCustomer().getId())
                .orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));

        Address managedAddress = new Address.Builder()
                .copy(address)
                .setCustomer(managedCustomer)
                .build();

        return repository.save(managedAddress);
    }

    @Override
    public Address read(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Address update(Address address) {
        if (repository.existsById(address.getId())) {
            return repository.save(address);
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Address> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Address> findByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId);
    }
}
