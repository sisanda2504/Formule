/*
OrderItemRepository.java
Author: Tsholofelo Mabidikane (230018165)
Date: 16 October 2025
*/
package za.ac.cput.repository.business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.business.OrderItem;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrderId(Long orderId);

}