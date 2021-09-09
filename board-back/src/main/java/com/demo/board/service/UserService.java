package com.demo.board.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.demo.board.vo.UserVO;

public interface UserService extends UserDetailsService {
	String login(UserVO userVO,HttpServletResponse response);
	String singup(UserVO userVO);
	List<UserVO> users();
}
