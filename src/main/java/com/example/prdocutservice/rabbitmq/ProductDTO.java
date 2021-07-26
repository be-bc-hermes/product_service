package com.example.prdocutservice.rabbitmq;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private Long id;
    private String priceType;
    private double oldPrice;
    private double newPrice;
}
