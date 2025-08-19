package za.ac.cput.service.users;

import za.ac.cput.domain.users.Admin;
import za.ac.cput.service.IService;

import java.util.List;

public interface IAdminService extends IService<Admin,Integer> {

    boolean deleteAdmin(Integer id);
    List<Admin> getAll();
}
