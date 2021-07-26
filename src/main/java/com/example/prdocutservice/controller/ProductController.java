package com.example.prdocutservice.controller;

import com.example.prdocutservice.entity.Product;
import com.example.prdocutservice.rabbitmq.ProductDTO;
import com.example.prdocutservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/product")
public class ProductController {

   @Autowired
    private IProductService productService;



    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllProduct(){
        return  ResponseEntity.ok(productService.getAllProduct());
    }


    @PutMapping()
    public ResponseEntity<String> updatePrice(@RequestBody ProductDTO productDTO){



        return new ResponseEntity(productService.update(productDTO), HttpStatus.OK);


    }



}
