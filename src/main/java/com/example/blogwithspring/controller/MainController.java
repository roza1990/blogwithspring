package com.example.blogwithspring.controller;

import com.example.blogwithspring.model.Category;
import com.example.blogwithspring.model.Post;
import com.example.blogwithspring.repository.CategoryRepositroy;
import com.example.blogwithspring.repository.PostRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class MainController {
    @Autowired
    private CategoryRepositroy categoryRepositroy;
    @Autowired
    private PostRepositroy postRepositroy;

    @GetMapping("/")
    public String main(ModelMap map){
        List<Category> all = categoryRepositroy.findAll();
        map.addAttribute("categories", all);
        List<Post> allPosts=postRepositroy.findAll();
        map.addAttribute("posts",allPosts);
        return "index";
    }

}
