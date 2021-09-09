package com.demo.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.demo.board.security.JwtAuthenticationFilter;
import com.demo.board.security.JwtTokenProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JwtTokenProvider jwtProvider;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
	        .httpBasic().disable() // rest api 만을 고려하여 기본 설정은 해제하겠습니다.
	        .csrf().disable() // csrf 보안 토큰 disable처리.
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 역시 사용하지 않습니다.
	        .and()
	        .authorizeRequests() // 요청에 대한 사용권한 체크
	        .antMatchers("/", "/css/**","/images/**","/js/**","/h2-console/**,","/profile").permitAll()
	        .antMatchers("/admin/**").hasRole("ADMIN")
	        .antMatchers("/*/login", "/*/signup").permitAll() // 가입 및 인증 주소는 누구나 접근가능
	        .antMatchers("/board/**").hasAnyRole("USER","ADMIN")
	        .anyRequest().permitAll() // 그외 나머지 요청은 누구나 접근 가능
	        .and()
	        .addFilterBefore(new JwtAuthenticationFilter(jwtProvider),UsernamePasswordAuthenticationFilter.class);
    		// JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
	}
    
}
