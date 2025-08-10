package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Category;
import za.ac.cput.service.ICategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final ICategoryService service;

    @Autowired
    public CategoryController(ICategoryService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Category create(@RequestBody Category category) {
        return service.create(category);
    }

    @GetMapping("/read/{id}")
    public Category read(@PathVariable Integer id) {
        return service.read(id);
    }

    @PutMapping("/update")
    public Category update(@RequestBody Category category) {
        return service.update(category);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @GetMapping("/getall")
    public List<Category> getAll() {
        return service.getAll();
    }

    @GetMapping("/search/name/{name}")
    public List<Category> findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @GetMapping("/search/description")
    public List<Category> searchByDescription(@RequestParam String keyword) {
        return service.searchByDescription(keyword);
    }
}

