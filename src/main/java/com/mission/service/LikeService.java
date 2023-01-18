package com.mission.service;


import com.mission.domain.entity.Like;
import com.mission.domain.entity.Post;
import com.mission.domain.entity.User;
import com.mission.exception.AppException;
import com.mission.repository.LikeRepository;
import com.mission.repository.PostRepository;
import com.mission.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mission.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public boolean addCount(Long postId, String userName) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(POST_NOT_FOUND, POST_NOT_FOUND.getMessage()));
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(USERNAME_NOT_FOUND, USERNAME_NOT_FOUND.getMessage()));

        likeRepository.findByPostAndUser(post, user)
                .ifPresent((like) -> {
                    throw new AppException(DUPLICATED_LIKE_COUNT, DUPLICATED_LIKE_COUNT.getMessage());
                });

        likeRepository.save(Like.builder()
                .post(post)
                .user(user)
                .build());

        return true;
    }

    public boolean deleteCount(Long postId, String userName) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(POST_NOT_FOUND, POST_NOT_FOUND.getMessage()));
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(USERNAME_NOT_FOUND, USERNAME_NOT_FOUND.getMessage()));
        Like like = likeRepository.findByPostAndUser(post, user)
                .orElseThrow(() -> new AppException(LIKE_NOT_FOUND, LIKE_NOT_FOUND.getMessage()));
        likeRepository.delete(like);
        return true;
    }

    public Long viewCount(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(POST_NOT_FOUND, POST_NOT_FOUND.getMessage()));

        return likeRepository.countByPost(post);
    }

    public List<Like> findAllPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(POST_NOT_FOUND, POST_NOT_FOUND.getMessage()));
        return likeRepository.findAllByPost(post);
    }


}
