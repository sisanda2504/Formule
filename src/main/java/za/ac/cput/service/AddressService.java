package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Address;
import za.ac.cput.repository.AddressRepository;
import za.ac.cput.service.IAddressService;

import java.util.List;

@Service
public class AddressService implements IAddressService {

    private final AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Address create(Address address) {
        return repository.save(address);
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
    public List<Address> findByCustomerId(int customerId) {
        return repository.findByCustomerId(customerId);
    }
}

