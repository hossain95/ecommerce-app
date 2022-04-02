package com.example.shoppingcard.service;

import com.example.shoppingcard.model.Category;
import com.example.shoppingcard.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listCategories(){
        return categoryRepository.findAll();
    }

    public ResponseEntity<Object> createCategory(Category category){
        try {
            categoryRepository.save(category);

            return new ResponseEntity<>("Category creation is successful!", HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>("Category creation is failed!", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> findByCategoryName(String categoryName){
        System.out.println("category name: "+ categoryName);
        try {
            Category category = categoryRepository.findByCategoryName(categoryName);
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Find by category name is failed!", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> findByCategoryId(Long categoryId){
        try {
            Category category = categoryRepository.findById(categoryId).get();
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
        catch (Exception e){
        return new ResponseEntity<>("Find by category id is failed!", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> updateCategoryById(Long categoryId, Category newCategory){

        try{
            Category category = categoryRepository.findById(categoryId).get();

            category.setCategoryName(newCategory.getCategoryName());
            category.setDescription(newCategory.getDescription());
            category.setImageUrl(newCategory.getImageUrl());
            categoryRepository.save(category);
            return new ResponseEntity<>("Category updated!", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Category does not updated!", HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
