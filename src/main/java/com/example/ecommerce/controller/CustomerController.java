package com.example.ecommerce.controller;

import com.example.ecommerce.model.Customer;
import com.example.ecommerce.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerRepository repository;
    public CustomerController(CustomerRepository repository){this.repository=repository;}
    @GetMapping public List<Customer> all(){return repository.findAll();}
}
