package com.example.prdocutservice.service;

import com.example.prdocutservice.entity.Product;
import com.example.prdocutservice.rabbitmq.Message;
import com.example.prdocutservice.rabbitmq.Producer;
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
    private Producer sendMessage;

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
        String priceType = "";
        Product product = productRepository.getById(id);

        try {
            Double oldPrice = 0.0;
            JSONObject data = new JSONObject(body);
            //System.out.println(data.keys());
            if (data.has("price")) {
                priceType = "web";
                oldPrice = product.getPrice();
                newPrice = data.getDouble("price");
                product.setPrice(newPrice);

            } else if (data.has("mobilePrice")) {
                priceType = "mobil";
                oldPrice = product.getMobilePrice();
                newPrice = data.getDouble("mobilePrice");
                product.setMobilePrice(newPrice);
            }
            updatedProductNotification(id, priceType, oldPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return productRepository.getById(id);
    }

    @Transactional
    public void updatedProductNotification(long id, String priceType, double oldPrice) {

        Product updatedProduct = new Product();
        updatedProduct = productRepository.getById(id);
        productRepository.save(updatedProduct);
        Message message = new Message();
        message.setId(UUID.randomUUID().toString());
        message.setCreatedAt(new Date());
        if (priceType.equalsIgnoreCase("web")) {
            message.setMessage("id" + ":" + id + "," + "priceType" + ":" + "DESKTOP" + "," + "oldPrice" + ":" + oldPrice + "," + "newPrice" + ":" + updatedProduct.getPrice());

        } else if (priceType.equalsIgnoreCase("mobil")) {
            message.setMessage("id" + ":" + id + "," + "priceType" + ":" + "MOBILE" + "," + "oldPrice" + ":" + oldPrice + "," + "newPrice" + ":" + updatedProduct.getMobilePrice());

        }

        sendMessage.sendToQueue(message);
        System.out.println(message.toString());

    }


}
