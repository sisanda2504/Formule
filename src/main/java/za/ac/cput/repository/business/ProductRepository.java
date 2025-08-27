package za.ac.cput.repository.business;

import za.ac.cput.domain.business.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByCategory(String category);
}
