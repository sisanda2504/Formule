package za.ac.cput.repository.business;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.business.CartItems;

import java.util.List;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {

    List<CartItems> findByCartId(Long cartId);
}
