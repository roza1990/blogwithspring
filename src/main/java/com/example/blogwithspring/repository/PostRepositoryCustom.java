package com.example.blogwithspring.repository;

import com.example.blogwithspring.model.Post;

import java.util.List;

public interface PostRepositoryCustom {
    List<Post> getPostsByCategoryId(int id);

}
