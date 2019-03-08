package com.example.blogwithspring.controller;

import com.example.blogwithspring.model.Post;
import com.example.blogwithspring.repository.CategoryRepositroy;
import com.example.blogwithspring.repository.PostRepositroy;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Controller
@RequestMapping(value= "/post")
public class PostController {
    @Value("${image.upload.dir}")
    private String imageUploadDir;
    @Autowired
    private PostRepositroy postRepositroy;
    @Autowired
    private CategoryRepositroy categoryRepositroy;

    @GetMapping("/delete")
    public String deleteById(@RequestParam("id") int id){
        Optional<Post> post=postRepositroy.findById(id);
        if(post.isPresent()){
            postRepositroy.deleteById(id);
        }
        return "redirect:/";
    }


    @GetMapping("/singlePage")
    public String singleView(ModelMap map, @RequestParam("id") int id){
      map.addAttribute("singleData", postRepositroy.getOne(id));
            return "single";
    }

@GetMapping("/add")
    public String addPostView(ModelMap map){
    map.addAttribute("categories", categoryRepositroy.findAll());
    return "addPost";
}

    @PostMapping("/add")
    public String addPost(@ModelAttribute Post post, @RequestParam("picture") MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File picture = new File(imageUploadDir + File.separator + fileName);
        file.transferTo(picture);
        post.setPicUrl(fileName);
        postRepositroy.save(post);
        return "redirect:/";
    }

    @GetMapping("/getImage")
    public void getImageAsByteArray(HttpServletResponse response, @RequestParam("picUrl") String picUrl) throws IOException {
        InputStream in = new FileInputStream(imageUploadDir + File.separator + picUrl);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

}
