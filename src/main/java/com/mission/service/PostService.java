package com.mission.service;

import com.mission.domain.entity.Post;
import com.mission.domain.entity.User;
import com.mission.exception.AppException;
import com.mission.exception.ErrorCode;
import com.mission.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                .content(content)
                .build();
        postRepository.save(post);

        return "SUCCESS";
    }
}
