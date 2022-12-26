package com.mission.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @PostMapping
    public ResponseEntity<String> writePost(){
        return ResponseEntity.ok().body("게시물이 등록 되었습니다.");
    }
}
