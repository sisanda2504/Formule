package za.ac.cput.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.business.Product;
import za.ac.cput.repository.business.ProductRepository;

@Service
public class ProductService implements IProductService {
    private final ProductRepository repository;
    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product findByCategory(String category) {
        return repository.findByCategory(category);
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
    public Product read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Product update(Product product) {
        return repository.save(product);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }
}
