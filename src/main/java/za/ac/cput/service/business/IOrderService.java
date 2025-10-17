/*IOrderService.java
Author: Tsholofelo Mabidikane (230018165)
Date: 07 August 2025
 */
package za.ac.cput.service.business;

import za.ac.cput.domain.business.Order;
import za.ac.cput.service.IService;
import za.ac.cput.domain.business.Cart;
import java.util.List;

public interface IOrderService extends IService<Order, Long> {

    List<Order> getAllOrders();
    List<Order> findOrdersByCustomerId(Long customerId);
    Order checkout(Cart cart);
    
}
