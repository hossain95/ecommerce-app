package com.example.shoppingcard.repository;

import com.example.shoppingcard.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.categoryName=?1")
    public Category findByCategoryName(String categoryName);
}
