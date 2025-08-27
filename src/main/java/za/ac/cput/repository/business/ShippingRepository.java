
package za.ac.cput.repository.business;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.business.Shipping;

import java.util.List;


public interface ShippingRepository extends JpaRepository<Shipping, Integer> {
    List<Shipping> findByOrderId(Integer orderId);
    List<Shipping> findByCustomerId(Integer customerId);
    List<Shipping> findByStatus(String status);
}
