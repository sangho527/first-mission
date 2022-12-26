package com.mission.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .httpBasic().disable()
//                .csrf().disable() // 크로스 사이트 기능
//                .cors().and() // 크로스 사이트 도메인이 다를때 허용해준다는 뜻
//                .authorizeRequests()
//                .antMatchers("/api/**").permitAll()
//                .antMatchers("/api/v1/users/join", "/api/v1/users/login").permitAll()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt 사용하는 경우 사용
//                .and()
//                //.addFilterBefore(new JwtTokenFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
}
