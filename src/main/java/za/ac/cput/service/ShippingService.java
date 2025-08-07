/*
ShippingService.java
Shipping model Service class
Author: Tsholofelo Mabidikane (230018165)
Date: 07 August 2025
 */
package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Shipping;
import za.ac.cput.repository.ShippingRepository;

import java.util.List;

@Service
public class ShippingService implements IShippingService {

    private ShippingRepository repository;

    @Autowired
    public ShippingService (ShippingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Shipping create(Shipping shipping) {
        return this.repository.save(shipping);
    }

    @Override
    public Shipping read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Shipping update(Shipping shipping) {
        return repository.save(shipping);
    }

    @Override
    public boolean deleteShipping(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Shipping> getAll() {
        return repository.findAll();
    }
}
