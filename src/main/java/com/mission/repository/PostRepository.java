package com.mission.repository;

import com.mission.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface PostRepository extends JpaRepository<Post,Long> {
    Page<Post> findByUserId(Pageable pageable, Long userId);

}
