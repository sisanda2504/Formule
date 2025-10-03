package za.ac.cput.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.users.Admin;
import za.ac.cput.domain.users.Customer;
import za.ac.cput.repository.users.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IAdminService {


    private AdminRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(AdminRepository repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Admin create(Admin admin) {
        admin = new Admin.Builder()
                .copy(admin)
                .setPassword(passwordEncoder.encode(admin.getPassword()))
                .build();
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

    @Override
    public Optional<Admin> login(String email, String password) {
        Optional<Admin> adminOpt = repository.findByEmailAddress(email);
        if(adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            if(passwordEncoder.matches(password, admin.getPassword())) {
                return Optional.of(admin);
            }
        }
        return Optional.empty();
    }

}
