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

    public static Product createProduct(String name,
                                        String description,
                                        String imageUrl,
                                        double price,
                                        int stockQuantity,
                                        int categoryId,
                                        Brands brand) {

        if (Helper.isNullOrEmpty(name) ||
                Helper.isNullOrEmpty(description) ||
                Helper.isNullOrEmpty(imageUrl) ||
                !Helper.isValidPrice(price) ||
                !Helper.isValidQuantity(stockQuantity) ||
                categoryId <= 0 ||
                brand == null) {
            return null;
        }

        return new Product.Builder()
                .setName(name)
                .setDescription(description)
                .setImage_url(imageUrl)
                .setPrice(price)
                .setStockQuantity(stockQuantity)
                .setCategoryId(categoryId)
                .setBrand(brand)
                .build();
    }
}
