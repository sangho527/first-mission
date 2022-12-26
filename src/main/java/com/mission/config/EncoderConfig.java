package com.mission.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class EncoderConfig {

    @Bean
    public BCryptPasswordEncoder encoder() { // 꼭 encoder 와 Security 는 다른 클래스로 작성해야함 이후에 순환 참조 문제 발생 할 수 있음
        return  new BCryptPasswordEncoder();

    }
}
