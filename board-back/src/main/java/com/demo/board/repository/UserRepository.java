package com.demo.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.board.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUserEmail(String email);
	User findByUserEmailAndUserPw(String email, String password);
}
