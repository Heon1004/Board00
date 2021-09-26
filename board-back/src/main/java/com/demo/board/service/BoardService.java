package com.demo.board.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.board.entity.Board;
import com.demo.board.vo.BoardVO;

public interface BoardService {
//	List<BoardDTO> getBoards();
	void write(BoardVO boardVO,HttpServletRequest request);
	void deletePost(Long boardId);
	Page<Board> list(Pageable pageable);
	int updateHitCount(Long boardId);
	Page<BoardVO> search(String keyword,Pageable pageable);
	Pageable update(Pageable pageable,BoardVO boardVO);
	int updateLikes(Long boardId);
	boolean isUser(Long boardId,HttpServletRequest request);
}
