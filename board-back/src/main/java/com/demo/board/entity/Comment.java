package com.demo.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name="Comment")
public class Comment {
	
	@Id @GeneratedValue
	@Column
	private long commentId;
	
	private String title;
	private String content;
	private String writer;
	
	@ManyToOne
	@JoinColumn(name = "boardId")
	private Board board;
}
