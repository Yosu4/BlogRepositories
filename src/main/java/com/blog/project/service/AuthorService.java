package com.blog.project.service;

import com.blog.project.dao.RequestAuthor;
import com.blog.project.model.AuthorDetails;
import com.blog.project.repository.AuthorRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepositories authorRepositories;

    public void addAuthor(RequestAuthor body) {
        AuthorDetails authorDetails = AuthorDetails.builder()
                .firstName(body.getFirstName())
                .lastName(body.getLastName())
                .isDelete(false)
                .build();

        authorRepositories.save(authorDetails);
    }
}
