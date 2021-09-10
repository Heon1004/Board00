package com.demo.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.board.service.UserService;
import com.demo.board.vo.UserVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// CrossOrigin : CORS 문제를 해결하기 위해 추가.
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("/login")
  	public String login(UserVO userVO,HttpServletResponse response) {
		log.info("USER LOGIN id : {} , pw : {} ", userVO.toString());
		return userService.login(userVO,response);
  	}
	
    
    @PostMapping("/singup")
  	public String singUp(UserVO userVO) {
    	log.info("id : {} , pw : {} , name : {}", userVO.toString());
        return userService.singup(userVO);
  	}
    
    @GetMapping("/logout")
    public boolean logoutPage(HttpServletRequest request, HttpServletResponse response) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if (auth != null) {
    		new SecurityContextLogoutHandler().logout(request, response, auth);
    	}
    	return true;
    }
    
	
}
