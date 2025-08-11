package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByCustomerId(int customerId);
    List<Address> findByCity(String city);
    List<Address> findByProvince(String province);
    List<Address> findByCountry(String country);
}
