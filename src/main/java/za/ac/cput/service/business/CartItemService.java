package za.ac.cput.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.business.CartItems;
import za.ac.cput.repository.business.CartItemsRepository;

import java.util.List;

@Service
public class CartItemService implements ICartItemService {

    private final CartItemsRepository repository;

    @Autowired
    public CartItemService(CartItemsRepository repository) {
        this.repository = repository;
    }

    @Override
    public CartItems create(CartItems cartItem) {
        return repository.save(cartItem);
    }

    @Override
    public CartItems read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public CartItems update(CartItems cartItem) {
        return repository.save(cartItem);
    }

    @Override
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<CartItems> findByCartId(Long cartId) {
        return repository.findByCartId(cartId);
    }

    @Override
    public List<CartItems> getAll() {
        return repository.findAll();
    }
}
