package za.ac.cput.repository;

import za.ac.cput.domain.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByCategory(String category);
}
