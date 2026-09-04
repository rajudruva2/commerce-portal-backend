package com.example.ecommerce.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="products")
public class Product {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false) private String name;
    private String description;
    @Column(nullable=false) private BigDecimal price;
    @Column(nullable=false) private Integer stock;
    @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="category_id")
    private Category category;
    @Column(nullable=false) private Boolean active = true;
    @Column(name="created_at", nullable=false) private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId(){return id;}
    public String getName(){return name;}
    public void setName(String v){name=v;}
    public String getDescription(){return description;}
    public void setDescription(String v){description=v;}
    public BigDecimal getPrice(){return price;}
    public void setPrice(BigDecimal v){price=v;}
    public Integer getStock(){return stock;}
    public void setStock(Integer v){stock=v;}
    public Category getCategory(){return category;}
    public void setCategory(Category v){category=v;}
    public Boolean getActive(){return active;}
    public void setActive(Boolean v){active=v;}
    public LocalDateTime getCreatedAt(){return createdAt;}
}
