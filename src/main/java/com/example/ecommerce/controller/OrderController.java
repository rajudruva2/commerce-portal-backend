package com.example.ecommerce.controller;

import com.example.ecommerce.model.*;
import com.example.ecommerce.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository orders;
    private final CustomerRepository customers;
    private final ProductRepository products;

    public OrderController(OrderRepository orders,CustomerRepository customers,ProductRepository products){
        this.orders=orders; this.customers=customers; this.products=products;
    }

    @GetMapping
    public List<CustomerOrder> all(){return orders.findAllByOrderByIdDesc();}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerOrder create(@RequestBody CreateOrderRequest request){
        Customer customer=customers.findById(request.customerId()).orElseThrow();
        CustomerOrder order=new CustomerOrder();
        order.setCustomer(customer);
        order.setStatus(OrderStatus.CONFIRMED);
        BigDecimal total=BigDecimal.ZERO;
        for(LineRequest line:request.items()){
            Product p=products.findById(line.productId()).orElseThrow();
            if(p.getStock()<line.quantity()) throw new IllegalArgumentException("Insufficient stock for "+p.getName());
            p.setStock(p.getStock()-line.quantity());
            products.save(p);
            OrderItem item=new OrderItem();
            item.setOrder(order); item.setProduct(p); item.setQuantity(line.quantity()); item.setUnitPrice(p.getPrice());
            order.getItems().add(item);
            total=total.add(p.getPrice().multiply(BigDecimal.valueOf(line.quantity())));
        }
        order.setTotalAmount(total);
        return orders.save(order);
    }

    @PutMapping("/{id}/status")
    public CustomerOrder status(@PathVariable Long id,@RequestParam OrderStatus value){
        CustomerOrder order=orders.findById(id).orElseThrow();
        order.setStatus(value);
        return orders.save(order);
    }

    public record CreateOrderRequest(Long customerId,List<LineRequest> items){}
    public record LineRequest(Long productId,Integer quantity){}
}
