package com.demo.board.vo;

import java.time.LocalDateTime;

import com.demo.board.entity.Board;
import com.demo.board.entity.Comment;
import com.demo.board.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentVO {
	private Long commentId;
	private Long boardId;
	private Long userId;
	private String content;
	private String writer;
	private int likes;
	private LocalDateTime regDate;
	private LocalDateTime updateDate; 
	
	public Comment toEntity() {
		return Comment.builder()
				.commentId(commentId)
				.boardId(boardId)
				.userId(userId)
				.content(content)
				.writer(writer)
				.likes(likes)
				.regDate(regDate)
				.updateDate(updateDate)
				.build();
	}
}
