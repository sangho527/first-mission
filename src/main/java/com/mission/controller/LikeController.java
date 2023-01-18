package com.mission.controller;

import com.mission.domain.dto.Response;
import com.mission.service.LikeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class LikeController {

    private final LikeService likeService;

    @ApiOperation(value = "좋아요 누르기")
    @PostMapping("/{postId}/likes")
    public ResponseEntity<Response<String>> addCount(@PathVariable Long postId, Authentication authentication) {
        likeService.addCount(postId, authentication.getName());
        return ResponseEntity.ok().body(Response.success("좋아요를 눌렀습니다."));
    }

    @ApiOperation(value = "좋아요 취소")
    @DeleteMapping("/{postId}/likes")
    public ResponseEntity<Response<String>> deleteCount(@PathVariable Long postId, Authentication authentication) {
        likeService.deleteCount(postId, authentication.getName());
        return ResponseEntity.ok().body(Response.success("좋아요를 취소했습니다."));
    }

    @ApiOperation(value = "좋아요 개수 조회")
    @GetMapping("/{postId}/likes")
    public ResponseEntity<Response<Long>> viewCount(@PathVariable Long postId) {
        Long likeCnt = likeService.viewCount(postId);
        return ResponseEntity.ok().body(Response.success(likeCnt));
    }


}
