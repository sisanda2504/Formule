package za.ac.cput.service;

import za.ac.cput.domain.Category;

import java.util.List;

public interface ICategoryService {
    Category create(Category category);
    Category read(int id);
    Category update(Category category);
    boolean delete(int id);
    List<Category> getAll();
    List<Category> findByName(String name);
    List<Category> searchByDescription(String keyword);
}

