package za.ac.cput.repository.business;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.business.Cart;
import za.ac.cput.domain.business.CartItems;
import za.ac.cput.domain.business.Product;

import java.util.List;
import java.util.Optional;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {

    List<CartItems> findByCartId(Long cartId);

    Optional<CartItems> findByCartAndProduct(Cart cart, Product product);
}
