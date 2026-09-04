package com.example.ecommerce.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
public class CustomerOrder {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;
    @Enumerated(EnumType.STRING) @Column(nullable=false)
    private OrderStatus status;
    @Column(name="total_amount", nullable=false)
    private BigDecimal totalAmount;
    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt=LocalDateTime.now();
    @OneToMany(mappedBy="order", fetch=FetchType.EAGER)
    private List<OrderItem> items=new ArrayList<>();

    public Long getId(){return id;}
    public Customer getCustomer(){return customer;}
    public void setCustomer(Customer v){customer=v;}
    public OrderStatus getStatus(){return status;}
    public void setStatus(OrderStatus v){status=v;}
    public BigDecimal getTotalAmount(){return totalAmount;}
    public void setTotalAmount(BigDecimal v){totalAmount=v;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public List<OrderItem> getItems(){return items;}
}
