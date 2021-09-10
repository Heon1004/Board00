package com.demo.board.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.board.entity.Board;
import com.demo.board.vo.BoardVO;

public interface BoardService {
//	List<BoardDTO> getBoards();
	void write(BoardVO boardVO,HttpServletRequest request);
	void deletePost(long boardId);
	Page<Board> list(Pageable pageable);
	int updateHitCount(long boardId);
	Page<BoardVO> search(String keyword,Pageable pageable);
	Pageable update(Pageable pageable,BoardVO boardVO);
}
