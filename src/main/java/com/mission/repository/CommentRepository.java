package com.mission.repository;


import com.mission.domain.entity.Comment;
import com.mission.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Transactional
    void deleteAllByPost(Post post);
    Page<Comment> findAllByPost(Post post, Pageable pageable);
}
