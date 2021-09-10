package com.demo.board.vo;

import java.time.LocalDateTime;
import java.util.List;

import com.demo.board.entity.Board;
import com.demo.board.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
public class CommentVO {
	private long commentId;
	private String title;
	private String content;
	private User user;
	private Board board;
	private int likes;
	private LocalDateTime regDate;
	private LocalDateTime updateDate; 
}
