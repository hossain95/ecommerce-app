package com.example.shoppingcard.controller;

import com.example.shoppingcard.dto.product.ProductDto;
import com.example.shoppingcard.model.Product;
import com.example.shoppingcard.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Object> createProduct(@RequestBody ProductDto productDto){
        System.out.println(productDto.getName());
        return productService.createProduct(productDto);
    }

    @GetMapping("/list")
    public List<Product> listProducts(){
        return productService.listProduct();
    }

}
