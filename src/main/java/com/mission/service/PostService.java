package com.mission.service;

import com.mission.domain.dto.post.PostDto;
import com.mission.domain.dto.post.PostRequest;
import com.mission.domain.entity.Post;
import com.mission.domain.entity.User;
import com.mission.exception.AppException;
import com.mission.repository.CommentRepository;
import com.mission.repository.PostRepository;
import com.mission.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.mission.exception.ErrorCode.USERNAME_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public PostDto writePost(PostRequest postRequest, String userName){
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(USERNAME_NOT_FOUND, USERNAME_NOT_FOUND.getMessage()));
        Post savedPost = postRepository.save(postRequest.toEntity(user));
        return PostDto.toPostDto(savedPost);
    }

    //        postRepository.findByTitle(content)
//                .ifPresent(post -> {
//                    throw new AppException(ErrorCode.INVALID_PERMISSION, "잘못된 권한입니다.");
//                });
        // 저장 하기
//        Post post = Post.builder()
//                .title(title)
//                .body(body)
//                .build();
//        postRepository.save(post);
//
//        return "SUCCESS";
//    }
}
