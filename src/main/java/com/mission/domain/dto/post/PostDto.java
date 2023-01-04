package com.mission.domain.dto.post;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostDto {
    private Long id;
    private String title;
    private String body;
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    public PostDto(Long id, String title, String body, String userName, LocalDateTime createdAt, LocalDateTime lastModifiedAt){
        this.id = id;
        this.title = title;
        this.body = body;
        this.userName = userName;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }
}
