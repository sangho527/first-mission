package com.mission.controller;


import com.mission.domain.dto.Response;
import com.mission.domain.dto.post.PostDto;
import com.mission.domain.dto.post.PostCreateRequest;
import com.mission.domain.dto.post.PostResponse;
import com.mission.domain.dto.post.PostUpdateRequest;
import com.mission.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()// 작성하는 거
    public ResponseEntity<Response<PostResponse>> writePost(@RequestBody PostCreateRequest postCreateRequest, Authentication authentication ){
        PostDto postDto = postService.writePost(postCreateRequest, authentication.getName());
        return ResponseEntity.ok().body(Response.success(new PostResponse("포스트 등록 완료", postDto.getId())));
    }

    @GetMapping("/{postId}") // 포스트 상세 조회
    public ResponseEntity<Response<PostDto>>findById(@PathVariable Long postId) {
        PostDto postDto = postService.findDetail(postId);
        return ResponseEntity.ok().body(Response.success(postDto));
    }

    @GetMapping // 포스트 전체 목록 조회 20개 까지
    public ResponseEntity<Response<Page<PostDto>>> getPostList(@PageableDefault(size = 20)
                                                               @SortDefault(sort = "registeredAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostDto> postDtos = postService.getAllItems(pageable);
        return ResponseEntity.ok().body(Response.success(postDtos));
    }

    @PutMapping("/{postId}") // 포스트 수정
    public ResponseEntity<Response<PostResponse>>update(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdateRequest, Authentication authentication) {
        PostDto postDto = postService.update(postId, authentication.getName(), postUpdateRequest.getTitle(), postUpdateRequest.getBody());
        return ResponseEntity.ok().body(Response.success(new PostResponse("포스트 수정 완료", postId)));
    }

    @DeleteMapping("/{postId}") // 포스트 삭제 , soft delete 사용, post, like, comment도 함께 삭제
    public ResponseEntity<Response<PostResponse>> deleteById(@PathVariable Long postId, Authentication authentication) {
        postService.delete(authentication.getName(), postId);
        return ResponseEntity.ok().body(Response.success(new PostResponse("포스트 삭제 완료", postId)));
    }
}