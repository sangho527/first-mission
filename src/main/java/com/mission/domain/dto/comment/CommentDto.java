package com.mission.domain.dto.comment;

import com.mission.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private Long postId;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    public CommentDto(Long id, Long postId, String comment, LocalDateTime createdAt, LocalDateTime lastModifiedAt){
        this.id = id;
        this.postId = postId;
        this.comment = comment;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    public static CommentDto toCommentDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .postId(comment.getPost().getId())
                .comment(comment.getComment())
                .createdAt(comment.getCreatedAt())
                .lastModifiedAt(comment.getLastModifiedAt())
                .build();

    }
}
