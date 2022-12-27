package com.mission.repository;


import com.mission.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Optional을 사용하면 반복적인 null 체크를 줄일 수 있다.
    Optional<User> findByUserName(String userName); // 값이 있으면 User 안에 값이 들어오고 없으면 들어오지 않음

}
