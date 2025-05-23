package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Admin;
import za.ac.cput.repository.AdminRepository;

import java.util.List;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private static IAdminService service;
    private AdminRepository repository;

    @Override
    public Admin create(Admin admin) {
        return this.repository.save(admin);
    }

    @Override
    public Admin read(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public Admin update(Admin admin) {
        return this.repository.save(admin);
    }

    @Override
    public boolean deleteAdmin(Integer id) {
         this.repository.deleteById(id);
         return true;
    }

    @Override
    public List<Admin> getAll() {
        return this.repository.findAll();
    }
}
