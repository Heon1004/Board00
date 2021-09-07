package com.demo.board.vo;

import java.time.LocalDateTime;
import java.util.List;

import com.demo.board.entity.User;

import lombok.Getter;

@Getter
public class UserLoginVO {
	private final Long userId;
	private final List<String> roles;
	private final LocalDateTime createdDate;

	public UserLoginVO(User user) {
        this.userId = user.getUserId();
        this.roles = user.getRoles();
        this.createdDate = user.getRegDate();
    }

}
