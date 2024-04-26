package com.blog.project.controller;

import com.blog.project.Exception.IncorrectAuthorException;
import com.blog.project.Exception.NoArticleException;
import com.blog.project.dao.RequestAddBlog;
import com.blog.project.dao.RequestAuthor;
import com.blog.project.dao.RequestUpdateArticle;
import com.blog.project.dao.ResponseModel;
import com.blog.project.model.ArticleDetails;
import com.blog.project.service.AuthorService;
import com.blog.project.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.blog.project.util.ConstantUtil.FAILED;
import static com.blog.project.util.ConstantUtil.OK;

@Slf4j
@RestController
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    AuthorService authorService;

    @PostMapping("/addArticle")
    private ResponseModel createBlog(@RequestBody RequestAddBlog body){
        try {
            blogService.createBlog(body);
            return ResponseModel.builder()
                    .status(OK).build();
        } catch (Exception e){
            log.error("Error Add Article. Error Message : " +e.getMessage());
            return ResponseModel.builder()
                    .status(FAILED)
                    .bodyResponse(e.getMessage())
                    .build();
        }
    }

    @PutMapping("/updateArticle/{id}")
    public ResponseModel updateBlog(@PathVariable("id") Long id, @RequestBody RequestUpdateArticle body) {
        try {
            blogService.updateBlog(body, id);
            return ResponseModel.builder()
                    .status(OK)
                    .bodyResponse("Saved!")
                    .build();
        } catch (Exception e){
            log.error("Error Update Article. Error Message : " +e.getMessage());
            return ResponseModel.builder()
                    .status(FAILED)
                    .bodyResponse(e.getMessage())
                    .build();
        }
    }

    @PutMapping("/findArticleById/{id}")
    public ResponseModel findBlog(@PathVariable("id") Long id) {
        try {
            ArticleDetails articleDetails = blogService.findArticle(id);
            return ResponseModel.builder()
                    .status(OK)
                    .bodyResponse(articleDetails)
                    .build();
        } catch (Exception e){
            log.error("Error Find Article. Error Message : " +e.getMessage());
            return ResponseModel.builder()
                    .status(FAILED)
                    .bodyResponse(e.getMessage())
                    .build();
        }
    }

    @GetMapping("/getArticles")
    public Object getAllArticles(){
        try {
            return blogService.getAllArticles();
        } catch (Exception e){
            log.error("Error Get Articles. Error Message : " +e.getMessage());
            return ResponseModel.builder().status(FAILED).bodyResponse(e.getMessage()).build();
        }
    }

    @PutMapping("/deleteArticleById/{id}")
    public ResponseModel deleteBlog(@PathVariable("id") Long id) {
        try {
            blogService.deleteArticle(id);
            return ResponseModel.builder()
                    .status(OK)
                    .bodyResponse("Deleted!")
                    .build();
        } catch (Exception e){
            log.error("Error Delete Articles. Error Message : " +e.getMessage());
            return ResponseModel.builder()
                    .status(FAILED)
                    .bodyResponse(e.getMessage())
                    .build();
        }
    }

    //    Author API
    @PostMapping("/addAuthor")
    private ResponseModel addAuthor(@RequestBody RequestAuthor body){
        try {
            authorService.addAuthor(body);
            return ResponseModel.builder()
                    .status(OK).build();
        } catch (Exception e){
            log.error("Error Add Author. Error Message : " +e.getMessage());
            return ResponseModel.builder()
                    .status(FAILED)
                    .bodyResponse(e.getMessage())
                    .build();
        }
    }
}
