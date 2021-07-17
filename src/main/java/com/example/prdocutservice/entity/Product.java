package com.example.prdocutservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productName;

    @Column
    @NotNull
    private Double price;

    @Column
    private Double mobilePrice;

    public Double getMobilePrice() {
        return mobilePrice;
    }

    public void setMobilePrice(Double mobilePrice) {
        this.mobilePrice = mobilePrice;
    }

    @Column
    private String category;

    public Product() {
        price = 0.0;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", mobilePrice=" + mobilePrice +
                ", category='" + category + '\'' +
                '}';
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
