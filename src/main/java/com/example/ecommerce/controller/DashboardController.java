package com.example.ecommerce.controller;

import com.example.ecommerce.repository.*;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins="http://44.221.75.226:5173")
public class DashboardController {
    private final ProductRepository products;
    private final OrderRepository orders;
    private final CustomerRepository customers;

    public DashboardController(ProductRepository products,OrderRepository orders,CustomerRepository customers){
        this.products=products; this.orders=orders; this.customers=customers;
    }

    @GetMapping("/summary")
    public Map<String,Object> summary(){
        long lowStock=products.findAll().stream().filter(p->p.getActive() && p.getStock()<10).count();
        BigDecimal revenue=orders.findAll().stream()
                .filter(o->o.getStatus()!=com.example.ecommerce.model.OrderStatus.CANCELLED)
                .map(com.example.ecommerce.model.CustomerOrder::getTotalAmount)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        return Map.of(
            "products", products.findByActiveTrueOrderByIdDesc().size(),
            "customers", customers.count(),
            "orders", orders.count(),
            "lowStockProducts", lowStock,
            "revenue", revenue
        );
    }
}
