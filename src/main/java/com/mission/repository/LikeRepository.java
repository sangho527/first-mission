package com.mission.repository;

import com.mission.domain.entity.Like;
import com.mission.domain.entity.Post;
import com.mission.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Integer> {

    Optional<Like> findByPostAndUser(Post post, User user);

    List<Like> findAllByPost(Post post);

    Long countByPost(Post post);

    @Transactional
    void deleteAllByPost(Post post);
}