package za.ac.cput.service.business;

import za.ac.cput.domain.business.Cart;
import za.ac.cput.service.IService;

import java.util.List;
import java.util.Optional;

public interface ICartService extends IService<Cart, Long> {
    List<Cart> getAll();
    Optional<Cart> findByCustomerId(Long customerId);
}
