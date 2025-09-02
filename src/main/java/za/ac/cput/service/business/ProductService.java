package za.ac.cput.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.business.Product;
import za.ac.cput.repository.business.ProductRepository;

import java.util.List;

@Service
public class ProductService implements IProductService {
    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Product> getAll() {
        return repository.findAll();
    }


    @Override
    public Product create(Product product) {
        return repository.save(product);
    }

    @Override
    public Product read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Product update(Product product) {
        return repository.save(product);
    }

    @Override
    public boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        return  repository.findByCategoryId(categoryId);
    }
}
