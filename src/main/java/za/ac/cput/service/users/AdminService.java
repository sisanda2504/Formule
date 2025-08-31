package za.ac.cput.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.users.Admin;
import za.ac.cput.repository.users.AdminRepository;

import java.util.List;

@Service
public class AdminService implements IAdminService {


    private AdminRepository repository;
    @Autowired
    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public Admin create(Admin admin) {
        return this.repository.save(admin);
    }

    @Override
    public Admin read(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public Admin update(Admin admin) {
        return this.repository.save(admin);
    }

    @Override
    public boolean delete(Long id) {
        if(repository.existsById(id)) {
            this.repository.deleteById(id);
            return true;
        }
         return false;
    }

    @Override
    public List<Admin> getAll() {
        return this.repository.findAll();
    }
}
