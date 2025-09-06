/*
 * CartFactory.java
 * Cart factory class
 * Author: Sisanda Madikizela (230601774)
 * Date: 18/05/2025
 */
package za.ac.cput.factory.business;

import za.ac.cput.domain.business.Cart;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.domain.business.CartItems;

import java.util.Collections;
import java.util.List;

public class CartFactory {

    public static Cart createCart(Customer customer, List<CartItems> items) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        if (items == null || items.isEmpty()) {
            items = Collections.emptyList();
        }

        return new Cart.Builder()
                .setCustomer(customer)
                .setItems(items)
                .build();
    }
}
