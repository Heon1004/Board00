package com.demo.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.board.entity.Board;
import com.demo.board.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	@Query(value="SELECT * FROM Comment WHERE board_id = :boardId",nativeQuery = true)
	List<Comment> findByBoardId(@Param("boardId")Long boardId);
	
}
