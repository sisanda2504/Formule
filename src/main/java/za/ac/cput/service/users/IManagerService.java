package za.ac.cput.service.users;

import za.ac.cput.domain.users.Manager;
import za.ac.cput.service.IService;

import java.util.List;

public interface IManagerService extends IService<Manager, Long> {
    List<Manager> getAll();
}