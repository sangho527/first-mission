package com.mission.config;


import com.mission.domain.User;
import com.mission.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final UserService userService;

    @Value("${jwt.secret}")
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable() // 인증을 ui로 하는 것이 아닌 http 로 하기때문에 basic이 아니라서 disable 했음
                .csrf().disable()
                .cors().and()
                .authorizeHttpRequests() // 요청 승인
                .antMatchers("/api/v1/users/join", "api/v1/users/login").permitAll() // join, login은 언제나 가능 왜냐면 아예 접근이 안될 수 있음 일단 회원가입이랑 로그인을 해야 글을 쓸 수 있으니까
                .antMatchers(HttpMethod.POST, "/api/v1/**").authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt 사용하는 경우 사용
                .and()
                .addFilterBefore(new JwtTokenFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
