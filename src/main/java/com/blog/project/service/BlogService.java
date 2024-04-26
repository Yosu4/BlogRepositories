package com.blog.project.service;

import com.blog.project.Exception.IncorrectAuthorException;
import com.blog.project.Exception.NoArticleException;
import com.blog.project.Exception.NoAuthorException;
import com.blog.project.dao.RequestAddBlog;
import com.blog.project.dao.RequestUpdateArticle;
import com.blog.project.model.ArticleDetails;
import com.blog.project.model.AuthorDetails;
import com.blog.project.repository.ArtickelRepositories;
import com.blog.project.repository.AuthorRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    ArtickelRepositories artickelRepositories;

    @Autowired
    AuthorRepositories authorRepositories;

    public void createBlog(RequestAddBlog body) throws NoAuthorException {
        Optional<AuthorDetails> authorDetails = authorRepositories.findById(body.getAuthorId());
        if(authorDetails.isEmpty()){
            throw new NoAuthorException("Author is Invalid for Create An Article");
        }
        ArticleDetails articleDetails = ArticleDetails.builder()
                .authorId(body.getAuthorId())
                .title(body.getTitle())
                .content(body.getContent())
                .description(body.getDescription())
                .createdAt(new Date())
                .updatedAt(new Date())
                .isDelete(false)
                .build();

        artickelRepositories.save(articleDetails);
    }

    public void updateBlog(RequestUpdateArticle body, Long id) throws IncorrectAuthorException, NoArticleException {
        Optional<ArticleDetails> articleDetails = artickelRepositories.findById(id);
        if(articleDetails.isPresent()){
            if(Objects.equals(body.getAuthorId(), articleDetails.get().getAuthorId())){
                ArticleDetails updatedArticle = articleDetails.get();
                updatedArticle.setUpdatedAt(new Date());
                updatedArticle.setContent(body.getContent());
                updatedArticle.setTitle(body.getTitle());
                updatedArticle.setDescription(body.getDescription());
                artickelRepositories.save(updatedArticle);
            } else {
                throw new IncorrectAuthorException("Unauthorise Author");
            }
        } else {
            throw new NoArticleException("Unauthorise Author");
        }
    }

    public ArticleDetails findArticle(Long id) throws NoArticleException {
        Optional<ArticleDetails> articleDetails = artickelRepositories.findById(id);
        if(articleDetails.isEmpty()){
            throw new NoArticleException("No Article Found!");
        }
        return articleDetails.get();
    }

    public List<ArticleDetails> getAllArticles() {
        return artickelRepositories.findActiveArticles();
    }

    public void deleteArticle(Long id) throws NoArticleException {
        Optional<ArticleDetails> articleDetails = artickelRepositories.findById(id);
        if(articleDetails.isEmpty()){
            throw new NoArticleException("No Article to delete");
        }
        ArticleDetails deletedArticle = articleDetails.get();
        deletedArticle.setIsDelete(true);
        artickelRepositories.save(deletedArticle);
    }
}
