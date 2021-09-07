package com.demo.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.board.vo.BoardVO;

public interface BoardService {
//	List<BoardDTO> getBoards();
	void write(BoardVO boardVO);
	void deletePost(long boardId);
	Page<BoardVO> list(Pageable pageable);
	BoardVO detail(long boardId);
	Page<BoardVO> search(String keyword,Pageable pageable);
	Pageable update(Pageable pageable,BoardVO boardVO);
}
