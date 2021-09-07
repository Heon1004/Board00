package com.demo.board.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.demo.board.entity.User;
import com.demo.board.vo.UserVO;

public interface UserService extends UserDetailsService {
	String login(UserVO userVO);
	String singup(UserVO userVO);
	List<UserVO> users();
}
