package com.demo.board.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

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
		boardVO.setUser(user.get());
		boardRepository.save(boardVO.toEntity());
	}

	@Override
	@Transactional
	public void deletePost(long boardId) {
		boardRepository.deleteById(boardId);
	}

	@Override
	@Transactional
	public Page<Board> list(@PageableDefault(size = 10, sort = {
			"boardId" },direction = Sort.Direction.DESC) Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber()-1;
		pageable = PageRequest.of(page, 10, Sort.by("boardId").descending());
		log.info(pageable.toString());
		Page<Board> boardlist = boardRepository.findAll(pageable);
		log.info(boardlist.toString());
		return boardlist;
	}

	@Override
	@Transactional
	public int updateHitCount(long boardId) {
		return boardRepository.findByHitCount(boardId);
	}

	@Override
	@Transactional
	public Page<BoardVO> search(String keyword,
			@PageableDefault(size=10,sort="boardId", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Board> result = boardRepository.findByTitleOrContentLike(keyword, pageable);
		Page<BoardVO> searchlist = result.map(
				board -> new BoardVO(
						board.getBoardId(),
						board.getTitle(),
						board.getContent(),
						board.getWriter(),
						board.getRegDate(),
						board.getUpdateDate(),
						board.getUser(),
						board.getHitCount(),
						board.getComment(),
						board.getLikes()
						)
				);
		return searchlist;
	}

	@Override
	public Pageable update(Pageable pageable, BoardVO boardVO) {
			boardRepository.save(boardVO.toEntity());
		return pageable;
	}

}
