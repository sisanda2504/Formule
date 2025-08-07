/*OrderFactory.java
Order model Factory class
Author: Tsholofelo Mabidikane (230018165)
Date: 23 April 2025
 */
package za.ac.cput.factory;

import za.ac.cput.domain.Order;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class OrderFactory {

    public static Order createOrder(int id, int customerId, LocalDate orderDate, Double totalAmount) {

        if(orderDate == null || totalAmount == null)
            return null;

        return new Order.Builder()
                .setId(id)
                .setCustomerId(customerId)
                .setOrderDate(orderDate)
                .setTotalAmount(totalAmount)
                .build();
    }
}
