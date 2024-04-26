package com.blog.project.repository;

import com.blog.project.model.AuthorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepositories extends JpaRepository<AuthorDetails, Long> {
}
