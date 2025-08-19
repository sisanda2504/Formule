/*IOrderService.java
Author: Tsholofelo Mabidikane (230018165)
Date: 07 August 2025
 */
package za.ac.cput.service.business;

import za.ac.cput.domain.business.Order;
import za.ac.cput.service.IService;

import java.util.List;

public interface IOrderService extends IService<Order, Integer> {

    boolean deleteOrder(Integer id);
    List<Order> getAllOrders();
}
