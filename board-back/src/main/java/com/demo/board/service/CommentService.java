package com.demo.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.demo.board.vo.CommentVO;

public interface CommentService {
	List<CommentVO> getComments(Long boardId);
	void write(Long boardId,CommentVO commentVO,HttpServletRequest request);
}
