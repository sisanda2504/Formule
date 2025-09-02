package za.ac.cput.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.business.Cart;
import za.ac.cput.repository.business.CartRepository;

import java.util.List;

@Service
public class CartService implements ICartService {
    private final CartRepository repository;

    @Autowired
    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cart create(Cart cart) {
        return repository.save(cart);
    }

    @Override
    public Cart read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Cart update(Cart cart) {
        return repository.save(cart);
    }
    @Override
    public boolean  delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Cart> getAll() {
        return List.of();
    }
}

