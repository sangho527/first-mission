package com.mission.service;

import com.mission.domain.dto.post.PostResponse;
import com.mission.domain.entity.Post;
import com.mission.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    public String post(String title, String content){

//        postRepository.findByTitle(content)
//                .ifPresent(post -> {
//                    throw new AppException(ErrorCode.INVALID_PERMISSION, "잘못된 권한입니다.");
//                });
        // 저장 하기
        Post post = Post.builder()
                .title(title)
                .body(content)
                .build();
        postRepository.save(post);

        return "SUCCESS";
    }
}
