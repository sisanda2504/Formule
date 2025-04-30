/*OrderFactory.java
Order model Factory class
Author: Tsholofelo Mabidikane (230018165)
Date: 23 April 2025
 */
package za.ac.cput.factory;

import za.ac.cput.domain.Order;
import za.ac.cput.util.Helper;

import java.util.Date;

public class OrderFactory {

    public static Order createOrder(int id, int customerId, Date date, Double totalAmount) {

        if(date == null || totalAmount == null)
            return null;

        return new Order.Builder()
                .setId(id)
                .setCustomerId(customerId)
                .setDate(date)
                .setTotalAmount(totalAmount)
                .build();
    }
}
