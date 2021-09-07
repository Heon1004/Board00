package com.demo.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	
	Page<Board> findAll(Pageable pageable);
	
	@Query(
			value="SELECT b FROM Board AS b WHERE b.title LIKE %:keyword% OR b.content LIKE %:keyword%",
			countQuery="SELECT COUNT(b.boardId) FROM Board AS b WHERE b.title LIKE %:keyword% OR b.content LIKE %:keyword%"
		)
	Page<Board> findByTitleOrContentLike(@Param("keyword")String keyword,Pageable pageable);
	
	// update , delete Query시 @Modifying 어노테이션을 추가
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(
			value="UPDATE Board AS b SET b.hit_count = hit_count + 1 WHERE b.board_id = :boardId",
			nativeQuery = true
	)
	int findByHitCount(@Param("boardId")long boardId);
}
