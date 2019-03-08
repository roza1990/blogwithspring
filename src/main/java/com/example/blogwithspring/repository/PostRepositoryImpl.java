package com.example.blogwithspring.repository;

import com.example.blogwithspring.model.Post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class PostRepositoryImpl implements PostRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager = null;

    @Override
    public List<Post> getPostsByCategoryId(int id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM `post` " +
                "WHERE post.firstname = ?", Post.class);
        query.setParameter(1, id + "%");
        return query.getResultList();
    }
}
