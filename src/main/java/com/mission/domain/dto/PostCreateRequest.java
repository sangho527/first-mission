package com.mission.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class PostCreateRequest {
    private String userName;
    private String title;
    private String content;
}
