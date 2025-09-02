package za.ac.cput.service.business;

import za.ac.cput.domain.business.Category;
import za.ac.cput.service.IService;

import java.util.List;

public interface ICategoryService extends IService<Category,Long> {

    List<Category> getAll();
    List<Category> findByName(String name);
    List<Category> searchByDescription(String keyword);
}

