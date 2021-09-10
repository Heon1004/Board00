package com.demo.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.board.entity.Board;
import com.demo.board.service.BoardService;
import com.demo.board.vo.BoardVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//CrossOrigin : CORS 문제를 해결하기 위해 추가.
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	private final BoardService boardService;
	
	
	@PostMapping("/write")
	public void write(BoardVO boardVO,HttpServletRequest request) {
		log.info("write board");
		log.debug("TITLE = "+boardVO.getTitle()+"  CONTENT = "+boardVO.getContent()+"  WRITER ="+boardVO.getWriter());
		boardService.write(boardVO,request);
	}
	
	@PostMapping("/deletePost")
	public void deletePost(long boardId) {
		log.info("delete board");
		boardService.deletePost(boardId);
	}
	
	@GetMapping("/list")
	public Page<Board> getPage(Pageable pageable){
		return boardService.list(pageable);
	}
	
	@PostMapping("/updateHitCount")
	public int updateHitCount(long boardId) {
		return boardService.updateHitCount(boardId);
	}
	
	@GetMapping("/search")
	public Page<BoardVO> search(String keyword,Pageable pageable) {
		return boardService.search(keyword, pageable);
	}
	
	@GetMapping("/total")
	public int total(Pageable pageable) {
		return boardService.list(pageable).getTotalPages();
	}
	
	@PostMapping("/updatePost")
	public Pageable update(Pageable pageable,BoardVO boardVO) {
		return boardService.update(pageable, boardVO);
	}
	
	
}
