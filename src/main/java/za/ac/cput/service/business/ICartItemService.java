package za.ac.cput.service.business;

import za.ac.cput.domain.business.CartItems;
import za.ac.cput.service.IService;

import java.util.List;

public interface ICartItemService extends IService<CartItems, Long> {

    List<CartItems> findByCartId(Long cartId);
    List<CartItems> getAll();
}
