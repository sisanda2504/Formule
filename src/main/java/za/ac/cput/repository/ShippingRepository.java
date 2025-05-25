
package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Shipping;

import java.util.List;


public interface ShippingRepository extends JpaRepository<Shipping, Integer> {
    List<Shipping> findByOrderId(Integer orderId);
    List<Shipping> findByCustomerId(Integer customerId);
    List<Shipping> findByStatus(String status);
}
