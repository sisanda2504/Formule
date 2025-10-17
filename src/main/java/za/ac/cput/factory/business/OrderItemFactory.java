package za.ac.cput.factory.business;

import za.ac.cput.domain.business.Order;
import za.ac.cput.domain.business.OrderItem;
import za.ac.cput.domain.business.Product;
import za.ac.cput.util.Helper;

public class OrderItemFactory {

    public static OrderItem createOrderItem(Order order, Product product, int quantity, double itemTotal) {
        if (order == null || product == null || quantity <= 0 || !Helper.isValidAmount(itemTotal)) {
            return null;
        }

        return new OrderItem.Builder()
                .setOrder(order)
                .setProduct(product)
                .setQuantity(quantity)
                .setItemTotal(itemTotal)
                .build();
    }
}