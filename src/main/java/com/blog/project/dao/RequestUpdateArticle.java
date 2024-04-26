package com.blog.project.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RequestUpdateArticle {
    private String title;
    private String content;
    private Long authorId;
    private String description;
}
