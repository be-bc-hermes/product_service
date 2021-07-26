package com.example.prdocutservice.service;

import com.example.prdocutservice.entity.Product;
import com.example.prdocutservice.rabbitmq.Message;
import com.example.prdocutservice.rabbitmq.Producer;
import com.example.prdocutservice.rabbitmq.ProductDTO;
import com.example.prdocutservice.repository.ProductRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class IProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    @Autowired
    private Producer producer;

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
    public Product update(ProductDTO productDTO) {
        Product product = productRepository.getById(productDTO.getId());

        try {
            if (productDTO.getPriceType().equalsIgnoreCase("DESKTOP")) {
                product.setPrice(productDTO.getNewPrice());

            } else if (productDTO.getPriceType().equalsIgnoreCase("MOBILE")) {
                product.setMobilePrice(productDTO.getNewPrice());

            }
            updatedProductNotification(productDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        productRepository.save(product);
        return productRepository.getById(productDTO.getId());
    }

    @Transactional
    public void updatedProductNotification(ProductDTO productDTO) {

        Message message = Message.builder().id(UUID.randomUUID().toString()).message(productDTO).build();
        producer.sendToQueue(message);
        System.out.println(message.toString());

    }


}
