package za.ac.cput.service.users;

import za.ac.cput.domain.users.Admin;
import za.ac.cput.service.IService;

import java.util.List;
import java.util.Optional;

public interface IAdminService extends IService<Admin,Long> {

    List<Admin> getAll();
    Optional<Admin> login(String email, String password);
}
