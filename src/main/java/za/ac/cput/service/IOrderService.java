/*IOrderService.java
Author: Tsholofelo Mabidikane (230018165)
Date: 07 August 2025
 */
package za.ac.cput.service;

import za.ac.cput.domain.Order;
import java.util.List;

public interface IOrderService extends IService<Order, Integer> {

    boolean deleteOrder(Integer id);
    List<Order> getAllOrders();
}
