package com.blog.project.repository;

import com.blog.project.model.ArticleDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtickelRepositories extends JpaRepository<ArticleDetails, Long> {

    @Query(value = "SELECT * FROM article u where u.is_delete = false", nativeQuery = true)
    List<ArticleDetails> findActiveArticles();

    @Query(value = "SELECT * FROM article u where u.id = :id u.is_delete = false", nativeQuery = true)
    List<ArticleDetails> findActiveArticle(@Param("id") String keyword);
}
