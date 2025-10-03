package za.ac.cput.factory.business;

import za.ac.cput.domain.business.Cart;
import za.ac.cput.domain.business.CartItems;
import za.ac.cput.domain.business.Product;
import za.ac.cput.util.Helper;

public class CartItemsFactory {

    public static CartItems createCartItems(Product product, Cart cart, int quantity) {

        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (cart == null) {
            throw new IllegalArgumentException("Cart cannot be null");
        }

        if (!Helper.isValidQuantity(quantity) || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than 0");
        }

        return new CartItems.Builder()
                .setProduct(product)
                .setCart(cart)
                .setQuantity(quantity)
                .build();
    }
}
