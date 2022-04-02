package com.example.shoppingcard.controller;

import com.example.shoppingcard.model.Category;
import com.example.shoppingcard.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public List<Category> getCategories(){
        return categoryService.listCategories();
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Object> findByCategoryId(@RequestBody @PathVariable("categoryId") Long categoryId){
        return categoryService.findByCategoryId(categoryId);
    }

    @GetMapping("/category-name/{categoryName}")
    public ResponseEntity<Object> findByCategoryName(@RequestBody @PathVariable("categoryName") String categoryName){
        System.out.println("category Name: "+ categoryName);
        return categoryService.findByCategoryName(categoryName);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<Object> updateCategoryById(@RequestBody Category category, @PathVariable("categoryId") Long categoryId){
        return categoryService.updateCategoryById(categoryId, category);
    }
}
