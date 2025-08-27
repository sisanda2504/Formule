package za.ac.cput.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.business.Cart;
import za.ac.cput.repository.business.CartRepository;

import java.util.List;

@Repository
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
    public Cart read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Cart update(Cart cart) {
        return repository.save(cart);
    }
    @Override
    public void  delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Cart> getAll() {
        return List.of();
    }
}

