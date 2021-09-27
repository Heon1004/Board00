package com.demo.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	void updateHitCount(@Param("boardId")Long boardId);
	
	/*
		JPA 에서 조회를 실핼할 시에 1차 캐시를 확인해서 해당 엔티티가 1차 캐시에 존재한다면 데이터베이스에 접근하지 않고, 
		1차 캐시에 있는 엔티티를 반환합니다. 하지만 벌크 연산은 1차 캐시를 포함한 영속성 컨텍스트를 무시하고 바로 쿼리를 실행하기 때문에 
		영속석 컨텍스트는 데이터 변경을 알 수가 없습니다. 
		즉, 벌크 연산 실행 시 1차 캐시와 데이터베이스의 싱크가 일치하지 않게 되는 것입니다.
		clearAutomatically = true 벌크 연산 직후 자동으로 영속성 컨텍스트를 Clear 해줍니다. 
		그래서 조회를 실행하면 1차 캐시에 해당 엔티티가 존재하지 않기 때문에 데이터베이스로 쿼리를 실행하게 됩니다.
	 
	 */
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE Board AS b SET b.likes = likes + 1 WHERE b.board_id = :boardId", nativeQuery = true)
	int updateLikes(@Param("boardId")Long boardId);
}
