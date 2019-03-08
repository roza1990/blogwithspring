package com.example.blogwithspring.repository;

import com.example.blogwithspring.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositroy extends JpaRepository<Category,Integer> {
}
