package com.demo.board.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter  {

	private JwtTokenProvider jwtTokenProvider;

    // Jwt Provier 주입
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    	log.info("------ JWT doFilter ------");
    	log.info("request : {} , response : {}", request.getHeader("token"),response.getHeaderNames());
      // 헤더에서 JWT 를 받아옵니다.
      String token = jwtTokenProvider.resolveToken(request);
      log.info(jwtTokenProvider.resolveToken(request));
      // 유효한 토큰인지 확인합니다.
      if (token != null && jwtTokenProvider.validateToken(token)) {
    	  log.info("----- 유효한 토큰 조건문 ------");
        // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        log.info(authentication+" : getAuthentication 메소드 결과 ");
        // SecurityContext 에 Authentication 객체를 저장합니다.
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info(SecurityContextHolder.getContext()+" Securit ContextHolder Context 시큐리티 컨텍스트 홀더 확인");
        
      }
      chain.doFilter(request, response);
    }

}