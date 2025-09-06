package za.ac.cput.factory.business;

import za.ac.cput.domain.business.Cart;
import za.ac.cput.domain.business.CartItems;
import za.ac.cput.domain.business.Product;
import za.ac.cput.util.Helper;

public class CartItemsFactory {

    public static CartItems createCartItems(Product product, Cart cart, int quantity) {

        if (product == null || cart == null) {
            return null;
        }


        if (!Helper.isValidQuantity(quantity) || quantity <= 0) {
            return null;
        }

        if (product.getPrice() <= 0) {
            return null;
        }

        return new CartItems.Builder()
                .setProduct(product)
                .setCart(cart)
                .setQuantity(quantity)
                .build();
    }
}
