package com.demo.board.vo;

import java.time.LocalDateTime;

import com.demo.board.entity.Board;
import com.demo.board.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BoardVO {

	private Long boardId;
	private String title;
	private String content;
	private String writer;
	private LocalDateTime regDate;
	private LocalDateTime updateDate; 
	private long userId;
	private long hitCount;
	
	public Board toEntity() {
		return Board.builder()
				.boardId(boardId)
                .title(title)
                .content(content)
                .writer(writer)
                .regDate(regDate)
                .updateDate(updateDate)
                .userId(userId)
                .hitCount(hitCount)
                .build();
	}
	
	public BoardVO boardBuild(Board board) {
		return BoardVO.builder()
				.boardId(board.getBoardId())
				.title(board.getTitle())
				.content(board.getContent())
				.writer(board.getWriter())
				.regDate(board.getRegDate())
				.updateDate(board.getUpdateDate())
				.userId(userId)
				.hitCount(hitCount)
				.build();
	}
}