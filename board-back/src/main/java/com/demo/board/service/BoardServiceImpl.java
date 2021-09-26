package com.demo.board.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.board.entity.Board;
import com.demo.board.entity.User;
import com.demo.board.repository.BoardRepository;
import com.demo.board.repository.UserRepository;
import com.demo.board.security.JwtTokenProvider;
import com.demo.board.vo.BoardVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardRepository boardRepository;
	private final JwtTokenProvider jwtProvider;
	private final UserRepository userRepository;
	
	@Override
	@Transactional
	public void write(BoardVO boardVO,HttpServletRequest request) {
		String token = jwtProvider.resolveToken(request);
		Optional<User> user = userRepository.findById(Long.parseLong(jwtProvider.getUserPk(token)));
		boardVO.setWriter(user.get().getUserName());
		boardVO.setUserId(user.get().getUserId());
		boardRepository.save(boardVO.toEntity());
	}

	@Override
	@Transactional
	public void deletePost(Long boardId) {
		boardRepository.deleteById(boardId);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Board> list(Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber()-1;
		pageable = PageRequest.of(page, 10, Sort.by("boardId").descending());
		Page<Board> boardlist = boardRepository.findAll(pageable);
		return boardlist;
	}

	@Override
	@Transactional
	public int updateHitCount(Long boardId) {
		return boardRepository.updateHitCount(boardId);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<BoardVO> search(String keyword,
			@PageableDefault(size = 10, sort = "boardId", direction = Sort.Direction.DESC) Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber()-1;
		pageable = PageRequest.of(page, 10, Sort.by("boardId").descending());
		Page<Board> result = boardRepository.findByTitleOrContentLike(keyword,pageable);
		Page<BoardVO> searchlist = result.map(
				board -> new BoardVO(
						board.getBoardId(),
						board.getTitle(),
						board.getContent(),
						board.getWriter(),
						board.getRegDate(),
						board.getUpdateDate(),
						board.getUserId(),
						board.getHitCount(),
						board.getComments(),
						board.getLikes()
						)
				);
		return searchlist;
	}

	@Override
	@Transactional
	public Pageable update(Pageable pageable, BoardVO boardVO) {
			Optional<Board> board = boardRepository.findById(boardVO.getBoardId());
			boardVO.setUserId(board.get().getUserId());
			boardVO.setWriter(board.get().getWriter());
			boardVO.setRegDate(board.get().getRegDate());
			LocalDateTime now = LocalDateTime.now();
			boardVO.setUpdateDate(now);
			boardRepository.save(boardVO.toEntity());
		return pageable;
	}

	@Override
	@Transactional
	public int updateLikes(Long boardId) {
		return boardRepository.updateLikes(boardId);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isUser(Long boardId, HttpServletRequest request) {
		Optional<Board> board = boardRepository.findById(boardId);
		String token = jwtProvider.resolveToken(request);
		Optional<User> user = userRepository.findById(Long.parseLong(jwtProvider.getUserPk(token)));
		
		if(board.get().getUserId().equals(user.get().getUserId())) {
			return true;
		}
		
		return false;
	}
	
	

}
