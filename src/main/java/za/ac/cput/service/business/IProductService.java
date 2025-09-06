package za.ac.cput.service.business;

import za.ac.cput.domain.business.Product;
import za.ac.cput.service.IService;

import java.util.List;

public interface IProductService extends IService<Product, Long> {

    List<Product> findByCategoryId(int categoryId);
    Iterable<Product> getAll();
}
