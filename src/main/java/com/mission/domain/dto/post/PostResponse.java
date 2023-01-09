package com.mission.domain.dto.post;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private String message;
    private Long postId;
}
