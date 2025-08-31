package za.ac.cput.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.users.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
