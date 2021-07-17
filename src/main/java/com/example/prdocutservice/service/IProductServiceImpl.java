package com.example.prdocutservice.service;

import com.example.prdocutservice.entity.Product;
import com.example.prdocutservice.repository.ProductRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class IProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    public IProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }



    @Override
    public Product getProductById(Long id) {

        return productRepository.getById(id);
    }



    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product update(String body, Long id) {
        Double newPrice = null;
        Product product = productRepository.getById(id);
        try {
            JSONObject data = new JSONObject(body);
            newPrice = data.getDouble("price");
            product.setPrice(newPrice);
            updateProductPrice(id, newPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return productRepository.getById(id);
    }
    @Transactional
    public void updateProductPrice(long id, double price){

        Product updatedProduct= new Product();
        updatedProduct = productRepository.getById(id);
        productRepository.save(updatedProduct);
    }


}
