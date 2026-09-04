package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins="http://44.221.75.226:5173")
public class ProductController {
    private final ProductRepository repository;
    public ProductController(ProductRepository repository){this.repository=repository;}

    @GetMapping
    public List<Product> all(){return repository.findByActiveTrueOrderByIdDesc();}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product){product.setActive(true); return repository.save(product);}

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id,@RequestBody Product input){
        Product p=repository.findById(id).orElseThrow();
        p.setName(input.getName()); p.setDescription(input.getDescription());
        p.setPrice(input.getPrice()); p.setStock(input.getStock());
        return repository.save(p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Product p=repository.findById(id).orElseThrow();
        p.setActive(false); repository.save(p);
    }
}
