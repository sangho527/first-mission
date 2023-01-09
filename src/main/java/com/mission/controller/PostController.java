package com.mission.controller;


import com.mission.domain.dto.Response;
import com.mission.domain.dto.post.PostDto;
import com.mission.domain.dto.post.PostRequest;
import com.mission.domain.dto.post.PostResponse;
import com.mission.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()// 작성하는 거
    public ResponseEntity<Response<PostResponse>> writePost(@RequestBody PostRequest postRequest, Authentication authentication ){
        PostDto postDto = postService.writePost(postRequest, authentication.getName());
        return ResponseEntity.ok().body(Response.success(new PostResponse("포스트 등록 완료", postDto.getId())));
    }

//    @GetMapping() // 보여주는 거
//    public ResponseEntity<List<PostResponse>> getPostList(Pageable pageable){
//        return ResponseEntity.ok().body(postService.getPost(pageable));
//    }
}