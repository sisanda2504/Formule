/*
 * CartFactory.java
 * Cart factory class
 * Author: Sisanda Madikizela (230601774)
 * Date: 18/05/2025
 */
package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.CartItems;
import za.ac.cput.util.Helper;
import java.util.List;

public class CartFactory {
    public static Cart createCart(int id, Customer customer, List<CartItems> items, Double totalPrice) {
        if (customer == null) {
            return null;
        }
        if (items == null || items.isEmpty()) {
            return null;
        }
        if (!Helper.isValidPrice(totalPrice)
                || totalPrice <= 0) {
            return null;
        }

        return new Cart.Builder()
                .setId(id)
                .setCustomer(customer)
                .setItems(items)
                .setTotalPrice(totalPrice)
                .build();
    }
}
