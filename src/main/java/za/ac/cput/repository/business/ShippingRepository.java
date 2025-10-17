/*
ShippingRepository.java
Author: Tsholofelo Mabidikane (230018165)
Date: 25 May 2025
 */
package za.ac.cput.repository.business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.business.Shipping;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {

}
