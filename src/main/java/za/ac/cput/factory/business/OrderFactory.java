package za.ac.cput.factory.business;

import za.ac.cput.domain.business.Order;
import za.ac.cput.domain.business.OrderItem;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.List;

public class OrderFactory {

    public static Order createOrder(Customer customer, LocalDate orderDate, Double totalAmount, List<OrderItem> orderItems) {
        if (customer == null || !Helper.isValidDate(orderDate) || !Helper.isValidAmount(totalAmount)) {
            return null;
        }

        Order.Builder builder = new Order.Builder()
                .setCustomer(customer)
                .setOrderDate(orderDate)
                .setTotalAmount(totalAmount);

        if (orderItems != null && !orderItems.isEmpty()) {
            builder.setOrderItems(orderItems);
        }

        return builder.build();
    }

    public static Order createOrder(Customer customer, LocalDate orderDate, Double totalAmount) {
        return createOrder(customer, orderDate, totalAmount, null);
    }
}