package com.demo.board.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import com.demo.board.entity.Board;
import com.demo.board.repository.BoardRepository;
import com.demo.board.vo.BoardVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardRepository repository;
	
	@Override
	@Transactional
	public void write(BoardVO boardVO) {
		repository.save(boardVO.toEntity());
	}

	@Override
	@Transactional
	public void deletePost(long boardId) {
		repository.deleteById(boardId);
	}

	@Override
	@Transactional
	public Page<BoardVO> list(@PageableDefault(size = 10, sort = {
			"boardId" }, direction = Sort.Direction.DESC) Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber()-1;
		pageable = PageRequest.of(page, 10, Sort.by("boardId").descending());
		Page<Board> boardlist = repository.findAll(pageable);
		Page<BoardVO> paginglist = boardlist.map(
				board -> new BoardVO(
						board.getBoardId(),
						board.getTitle(),
						board.getContent(),
						board.getWriter(),
						board.getRegDate(),
						board.getUpdateDate(),
						board.getUserId(),
						board.getHitCount()
						)
				);
		return paginglist;
	}

	@Override
	@Transactional
	public BoardVO detail(long boardId) {
		repository.findByHitCount(boardId);
		Optional<Board> board = repository.findById(boardId);
		BoardVO post = BoardVO.builder()
				.boardId(board.get().getBoardId())
				.title(board.get().getTitle())
				.content(board.get().getContent())
				.writer(board.get().getWriter())
				.regDate(board.get().getRegDate())
				.updateDate(board.get().getUpdateDate())
				.userId(board.get().getUserId())
				.hitCount(board.get().getHitCount())
				.build();
		return post;
	}

	@Override
	@Transactional
	public Page<BoardVO> search(String keyword,
			@PageableDefault(size=10,sort="boardId", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Board> result = repository.findByTitleOrContentLike(keyword, pageable);
		Page<BoardVO> searchlist = result.map(
				board -> new BoardVO(
						board.getBoardId(),
						board.getTitle(),
						board.getContent(),
						board.getWriter(),
						board.getRegDate(),
						board.getUpdateDate(),
						board.getUserId(),
						board.getHitCount()
						)
				);
		return searchlist;
	}

	@Override
	public Pageable update(Pageable pageable, BoardVO boardVO) {
			repository.save(boardVO.toEntity());
		return pageable;
	}

}
