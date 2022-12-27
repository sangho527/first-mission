package com.mission.controller;


import com.mission.domain.dto.PostCreateRequest;
import com.mission.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()// 작성하는 거
    public ResponseEntity<String> writePost(@RequestBody PostCreateRequest dto){
        postService.post(dto.getTitle(), dto.getContent());
        return ResponseEntity.ok().body("게시물이 등록 되었습니다.");
    }
    @GetMapping() // 보여주는 거
    public ResponseEntity<String> getPostList(){
        return ResponseEntity.ok().body("post list");
    }
}
