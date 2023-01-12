package com.mission.config;

import com.mission.domain.dto.user.UserDto;
import com.mission.service.UserService;
import com.mission.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService; // 이 둘을 AuthenticationConfig로 넘겨준다.
    private final String secretKey;
    private String token;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException { // 권한을 부여하는 문
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization : {}", authorizationHeader);

        if (authorizationHeader == null || isNotStartBearer(authorizationHeader)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token;
        try {
            token = authorizationHeader.split(" ")[1].trim();
        } catch (Exception e) {
            log.info("token 추출에 실패했습니다.");
            filterChain.doFilter(request, response);
            return;
        }
        String userName = JwtUtil.getUserName(token, secretKey);
        log.info("userName : {}", userName);

        //userDetail 가져오기
        UserDto user = userService.getUserName(userName);

        if (JwtUtil.isExpired(token, secretKey)) {
            filterChain.doFilter(request, response);
            return;
        }

//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), null, List.of(new SimpleGrantedAuthority(user.getUserRole().toString())));
//        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken); // 권한 부여
//        filterChain.doFilter(request, response);
    }

    private static boolean isNotStartBearer(String authorizationHeader) {
        return !authorizationHeader.startsWith("Bearer ");
    }
}
