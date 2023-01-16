package com.mission.service;

import com.mission.domain.dto.post.PostDto;
import com.mission.domain.dto.post.PostCreateRequest;
import com.mission.domain.entity.Post;
import com.mission.domain.entity.User;
import com.mission.exception.AppException;
import com.mission.repository.CommentRepository;
import com.mission.repository.PostRepository;
import com.mission.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.mission.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public PostDto writePost(PostCreateRequest postCreateRequest, String userName){
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(USERNAME_NOT_FOUND, USERNAME_NOT_FOUND.getMessage()));
        Post savedPost = postRepository.save(postCreateRequest.toEntity(user));
        return PostDto.toPostDto(savedPost);
    }

    public PostDto findDetail(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new AppException(POST_NOT_FOUND, POST_NOT_FOUND.getMessage()));
        return PostDto.toPostDto(post);
    }


    public Page<PostDto> getAllItems(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);
        Page<PostDto> postDtos = PostDto.toDtoList(posts);
        return postDtos;
    }

    public PostDto update(Long postId, String userName, String title, String body) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(USERNAME_NOT_FOUND, USERNAME_NOT_FOUND.getMessage()));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(POST_NOT_FOUND, POST_NOT_FOUND.getMessage()));

        if (isMismatch(userName, post)) {
            throw new AppException(INVALID_PERMISSION, INVALID_PERMISSION.getMessage());
        }

        post.setTitle(title);
        post.setBody(body);
        Post updatedPost = postRepository.save(post);
        return PostDto.toPostDto(updatedPost);
    }

    public boolean delete(String userName, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(POST_NOT_FOUND, POST_NOT_FOUND.getMessage()));
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(USERNAME_NOT_FOUND, USERNAME_NOT_FOUND.getMessage()));

        if (isMismatch(userName, post)) {
            throw new AppException(INVALID_PERMISSION, INVALID_PERMISSION.getMessage());
        }
        commentRepository.deleteAllByPost(post);
        // likeRepository.deleteAllByPost(post);
        postRepository.delete(post);
        return true;
    }


//    public Page<PostDto> getMyPost(Pageable pageable, String userName) {
//
//        User user = userRepository.findByUserName(userName)
//                .orElseThrow(() -> new AppException(USERNAME_NOT_FOUND, USERNAME_NOT_FOUND.getMessage()));
//
//        Page<Post> posts = postRepository.findAllByUserId(pageable, user.getId());
//        Page<PostDto> myPosts = PostDto.toDtoList(posts);
//        return myPosts;
//    }

    private static boolean isMismatch(String userName, Post post) {
        return !Objects.equals(post.getUser().getUserName(), userName);
    }
}
