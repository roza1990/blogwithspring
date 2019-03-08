package com.example.blogwithspring.controller;

import com.example.blogwithspring.model.Category;
import com.example.blogwithspring.repository.CategoryRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryRepositroy categoryRepositroy;
    @GetMapping("/delete")
    public String deleteById(@RequestParam("id") int id){
        Optional<Category> cat=categoryRepositroy.findById(id);
        if (cat.isPresent()) {
            categoryRepositroy.deleteById(id);
        }
        return "redirect:/";
    }


    @GetMapping("/add")
    public String addCategory(){
    return "addCategory";
     }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category) {
        categoryRepositroy.save(category);
        return "redirect:/";
    }

    @GetMapping("/getPostsByCategory")
    public String getPostsByCategory(ModelMap map, @RequestParam("id") int id){

        return "postsByCategory";

    }
}
