package com.demo.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.board.entity.Board;
import com.demo.board.entity.Comment;
import com.demo.board.entity.User;
import com.demo.board.repository.BoardRepository;
import com.demo.board.repository.CommentRepository;
import com.demo.board.repository.UserRepository;
import com.demo.board.security.JwtTokenProvider;
import com.demo.board.vo.CommentVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

	private final JwtTokenProvider jwtProvider;
	private final CommentRepository commentRepository;
	private final UserRepository userRepository;
	private final BoardRepository boardRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<CommentVO> getComments(Long boardId) {
		List<Comment> comments = commentRepository.findByBoardId(boardId);
		List<CommentVO> commentList = new ArrayList<>();
		comments.forEach(index -> {
			CommentVO dto = CommentVO.builder()
					.commentId(index.getCommentId())
					.content(index.getContent())
					.writer(index.getWriter())
					.boardId(index.getBoardId())
					.userId(index.getUserId())
					.likes(index.getLikes())
					.regDate(index.getRegDate())
					.updateDate(index.getUpdateDate())
					.build();
			commentList.add(dto);
		});
		
		return commentList;
	}

	@Override
	@Transactional
	public void write(Long boardId, CommentVO commentVO,HttpServletRequest request) {
		String token = jwtProvider.resolveToken(request);
		Optional<User> user = userRepository.findById(Long.parseLong(jwtProvider.getUserPk(token)));
		commentVO.setUserId(user.get().getUserId());
		commentVO.setWriter(user.get().getUserName());
		commentVO.setBoardId(boardId);
		commentRepository.save(commentVO.toEntity());
	}

	
}
