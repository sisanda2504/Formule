package za.ac.cput.service;

import za.ac.cput.domain.Product;

public interface IProductService extends IService<Product, Integer> {

    // For example, methods to find products by category, price range, etc.
     Product findByCategory(String category);

    Iterable<Product> getAll();
}
