package com.blog.project.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ResponseModel {
    private String status;
    private Object bodyResponse;
}
