package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.domain.Product;
import za.ac.cput.service.ProductService;

@RestController
public class ProductController {
    private ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/create")

    public Product create(@RequestBody Product product) {
        return this.service.create(product);
    }

    @PostMapping("/read")
    public Product read(@RequestBody Integer id) {
        return this.service.read(id);
    }

    @PostMapping("/update")
    public Product update(@RequestBody Product product) {
        return this.service.update(product);
    }
    @PostMapping("/delete")
    public void delete(@RequestBody Integer id) {
        service.delete(id);
    }
    @PostMapping("/getAll")
    public Iterable<Product> getAll() {
        return this.service.getAll();
    }
}

