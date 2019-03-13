package com.example.blogwithspring.repository;

import com.example.blogwithspring.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  PostRepositroy extends JpaRepository<Post,Integer>,PostRepositoryCustom  {
    List<Post> findAllByCategoryId(int categoryId);
}

