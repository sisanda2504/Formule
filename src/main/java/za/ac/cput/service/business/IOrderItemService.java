package za.ac.cput.service.business;

import za.ac.cput.domain.business.OrderItem;
import za.ac.cput.service.IService;

import java.util.List;

public interface IOrderItemService extends IService<OrderItem, Long> {

    List<OrderItem> getAllOrderItems();

    List<OrderItem> findOrderItemsByOrderId(Long orderId);
}