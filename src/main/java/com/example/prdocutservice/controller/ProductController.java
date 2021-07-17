package com.example.prdocutservice.controller;

import com.example.prdocutservice.entity.Product;
import com.example.prdocutservice.service.IProductService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product")
public class ProductController {

   @Autowired
    private IProductService productService;

    //public ProductController(IProductService IProductService) {this.productService = IProductService;}

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


    /*@PutMapping("/product")
    Product updateProduct(RequestBody Product, Product product) {
        return productService.update(product);
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody String body, @PathVariable Long id){
        System.out.println(body);
        Product result =productService.update(body, id);
        return ResponseEntity.ok(result);

        //return new ResponseEntity(productService.update(body, id), HttpStatus.UPGRADE_REQUIRED);
    }


}
