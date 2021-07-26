package com.example.prdocutservice.service;

import com.example.prdocutservice.entity.Product;
import com.example.prdocutservice.rabbitmq.ProductDTO;
import org.json.JSONObject;

import java.util.List;

public interface IProductService {
    Product saveProduct(Product product);
    public List<Product> getAllProduct();
    public Product getProductById(Long id);
    public Product addProduct(Product product);
    public void deleteProduct(Long productId);
    public Product update(ProductDTO productDTO);

}
