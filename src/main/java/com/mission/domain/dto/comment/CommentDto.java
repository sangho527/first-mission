package com.mission.domain.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class CommentDto {

    private String comment;
    private String createdAt;
    private String lastModifiedAt;
}
