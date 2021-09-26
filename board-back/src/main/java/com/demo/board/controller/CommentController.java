package com.demo.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.board.entity.Board;
import com.demo.board.service.CommentService;
import com.demo.board.vo.CommentVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
	
	private final CommentService commentService;
	
	@GetMapping("/getComments")
	public List<CommentVO> getComments(Long boardId){
		return commentService.getComments(boardId);
	}
	
	@PostMapping("/write")
	public void write(Long boardId,CommentVO commentVO, HttpServletRequest request) {
		commentService.write(boardId, commentVO, request);
	}
	
	@PostMapping("/delete")
	public void delete(Long commentId) {
		
	}
}
