package com.blog.project.dao;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class RequestAddBlog {
    private String title;
    private String content;
    private Long authorId;
    private String description;
}
