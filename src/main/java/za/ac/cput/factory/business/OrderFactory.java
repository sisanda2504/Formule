/*OrderFactory.java
Order model Factory class
Author: Tsholofelo Mabidikane (230018165)
Date: 23 April 2025
 */
package za.ac.cput.factory.business;

import za.ac.cput.domain.business.Order;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class OrderFactory {

    public static Order createOrder(Customer customer, LocalDate orderDate, Double totalAmount) {

        if (customer == null || !Helper.isValidDate(orderDate) || !Helper.isValidAmount(totalAmount)) {
            return null;
        }

        return new Order.Builder()
                .setCustomer(customer)
                .setOrderDate(orderDate)
                .setTotalAmount(totalAmount)
                .build();
    }
}