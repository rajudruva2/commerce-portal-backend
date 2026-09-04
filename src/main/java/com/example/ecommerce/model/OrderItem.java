package com.example.ecommerce.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="order_items")
public class OrderItem {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JoinColumn(name="order_id", nullable=false) private CustomerOrder order;
    @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="product_id", nullable=false) private Product product;
    @Column(nullable=false) private Integer quantity;
    @Column(name="unit_price", nullable=false) private BigDecimal unitPrice;

    public Long getId(){return id;}
    public Product getProduct(){return product;}
    public void setProduct(Product v){product=v;}
    public Integer getQuantity(){return quantity;}
    public void setQuantity(Integer v){quantity=v;}
    public BigDecimal getUnitPrice(){return unitPrice;}
    public void setUnitPrice(BigDecimal v){unitPrice=v;}
    public CustomerOrder getOrder(){return order;}
    public void setOrder(CustomerOrder v){order=v;}
}
