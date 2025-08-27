package za.ac.cput.service.business;

import za.ac.cput.domain.business.Product;
import za.ac.cput.service.IService;

public interface IProductService extends IService<Product, Integer> {

    // For example, methods to find products by category, price range, etc.
     Product findByCategory(String category);

    Iterable<Product> getAll();
}
