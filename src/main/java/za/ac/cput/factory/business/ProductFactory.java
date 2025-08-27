/*
 * ProductFactory.java
 * Product factory class
 * Author: Sisanda Madikizela (230601774)
 * Date: 18/05/2025
 */
package za.ac.cput.factory.business;

import za.ac.cput.domain.business.Product;
import za.ac.cput.domain.business.Brands;
import za.ac.cput.util.Helper;

public class ProductFactory {
    public static Product createProduct(int id, String name, String description, double price, int quantity, int categoryId, Brands brand) {
        if (Helper.isNullOrEmpty(name)) {
            return null;
        }
        if (Helper.isNullOrEmpty(description)) {
            return null;
        }

        if (!Helper.isValidPrice(price)) {
            return null;
        }
        if (!Helper.isValidQuantity(quantity)) {
            return null;
        }
        if (categoryId <= 0) {
            return null;
        }
        if (brand == null) {
            return null;
        }

        return new Product.Builder()
                .setId(id)
                .setName(name)
                .setDescription(description)
                .setPrice(price)
                .setQuantity(quantity)
                .setCategoryId(categoryId)
                .setBrand(brand)
                .build();
    }
}