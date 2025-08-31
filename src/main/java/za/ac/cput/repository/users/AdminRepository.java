package za.ac.cput.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.users.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
