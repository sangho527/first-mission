package com.mission.controller;


import com.mission.domain.dto.post.PostRequest;
import com.mission.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

//    @PostMapping()// 작성하는 거
//    public ResponseEntity<String> writePost(@RequestBody PostRequest dto){
//        postService.writePost(dto.getTitle(), dto.getBody());
//        return ResponseEntity.ok().body("게시물이 등록 되었습니다.");
//    }

//    @GetMapping() // 보여주는 거
//    public ResponseEntity<List<PostResponse>> getPostList(Pageable pageable){
//        return ResponseEntity.ok().body(postService.getPost(pageable));
//    }
}