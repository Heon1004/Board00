package com.demo.board.vo;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.demo.board.entity.Board;
import com.demo.board.entity.User;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class UserVO {
	
	private Long userId;
	private String userEmail;
	private String userPw;
	private String userName;
	private LocalDateTime regDate;
    private List<String> roles;
    private int isAvailable;
	
	public User toEntity() {
		return User.builder()
				.userId(userId)
                .userEmail(userEmail)
                .userPw(userPw)
                .userName(userName)
                .roles(Collections.singletonList("ROLE_USER"))
                .regDate(regDate)
                .isAvailable(isAvailable)
                .build();
	}
	
	@Builder
	public UserVO(User user) {
		this.userId = user.getUserId();
		this.userEmail = user.getUserEmail();
		this.userName = user.getUserName();
		this.roles = user.getRoles();
		this.regDate = user.getRegDate();
		this.isAvailable = user.getIsAvailable();
	}

	
	
	
	
}
