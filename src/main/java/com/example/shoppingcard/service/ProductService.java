package com.example.shoppingcard.service;

import com.example.shoppingcard.controller.ApiResponse;
import com.example.shoppingcard.dto.ProductDto;
import com.example.shoppingcard.model.Category;
import com.example.shoppingcard.model.Product;
import com.example.shoppingcard.repository.CategoryRepository;
import com.example.shoppingcard.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public ResponseEntity<Object> createProduct(ProductDto productDto){
        try {
            Long categoryId = productDto.getCategoryId();

            Optional<Category> category = categoryRepository.findById(categoryId);
            if (!category.isPresent()){
                return new ResponseEntity<>(new ApiResponse(false, productDto.getName() + " does not exist!"), HttpStatus.CONFLICT);
            }

            Product product = new Product();
            product.setCategory(category.get());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setName(productDto.getName());
            product.setImageURL(productDto.getImageURL());

            productRepository.save(product);

            return new ResponseEntity<>(new ApiResponse(true, "product created successfully!"), HttpStatus.CREATED);

        }
        catch (Exception e){
            return new ResponseEntity<>("Product creation failed!", HttpStatus.BAD_REQUEST);
        }
    }

    public List<Product> listProduct(){
        return productRepository.findAll();
    }
}
