package com.demo.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "board")
public class Board {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardId;
	
	@NotNull
	@Column(length = 50)
	private String title;
	
	@NotNull
	@Column(length = 600)
	private String content;
	
	@NotNull
	@Column(length = 50)
	private String writer;
	
	@NotNull
	@JoinColumn(referencedColumnName = "user_id")
	private long userId;
	
	@Column(name = "regDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreationTimestamp
	private LocalDateTime regDate;
	
	@Column(name = "updateDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@UpdateTimestamp
	private LocalDateTime updateDate;
	
	@NotNull
	@Column(name = "hitCount")
	@ColumnDefault("0")
	private long hitCount;

	@Builder
	public Board(Long boardId, @NotNull String title, @NotNull String content, @NotNull String writer,
			LocalDateTime regDate,LocalDateTime updateDate, long userId, long hitCount) {
		this.boardId = boardId;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regDate = regDate;
		this.userId = userId;
		this.hitCount = hitCount;
	}
	
	
}
