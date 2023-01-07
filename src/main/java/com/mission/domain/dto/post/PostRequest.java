package com.mission.domain.dto.post;

import com.mission.domain.entity.Post;
import com.mission.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class PostRequest {
    private String title;
    private String body;

    public Post toEntity(User user) {
        return Post.builder()
                .title(this.title)
                .body(this.body)
                .user(user)
                .build();
    }
}
