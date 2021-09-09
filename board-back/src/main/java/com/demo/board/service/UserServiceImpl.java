package com.demo.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.board.entity.User;
import com.demo.board.exception.EmailLoginFailedCException;
import com.demo.board.repository.UserRepository;
import com.demo.board.security.JwtTokenProvider;
import com.demo.board.vo.UserVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private JwtTokenProvider jwtProvider;
	
	@Override
	@Transactional
	public String singup(UserVO userVO) {
		if(repository.findByUserEmail(userVO.getUserEmail()) != null) {
			log.info("重複するメールアドレスです。");
			return null;
		}
			log.info("email {} pass {} ",userVO.getUserEmail(),userVO.getUserPw());
			userVO.setUserPw(encoder.encode(userVO.getUserPw()));
			repository.save(userVO.toEntity());
			Optional<User> userReg = repository.findByUserEmail(userVO.getUserEmail());
			return jwtProvider.createJwtAccessToken(String.valueOf(userReg.get().getUserEmail()), userReg.get().getRoles());
	}

	@Override
	@Transactional
	public String login(UserVO userVO,HttpServletResponse res) throws EmailLoginFailedCException{
		log.debug("------LOGIN SERVICE------");
		Optional<User> user = repository.findByUserEmail(userVO.getUserEmail());
		log.debug("REPOSITORY {}",user.toString());
		if(!encoder.matches(userVO.getUserPw(), user.get().getUserPw())) {
			return null;
		}
		//리스폰스 헤더에 토큰을 추가함.예제로 잠깐 등록해봄
		
		res.addHeader("token", jwtProvider.createJwtAccessToken(String.valueOf(user.get().getUserId()), user.get().getRoles()));
		return jwtProvider.createJwtAccessToken(String.valueOf(user.get().getUserId()), user.get().getRoles());
	}

	@Override
	@Transactional
	public List<UserVO> users() {
		List<User> user = repository.findAll();
		List<UserVO> users = new ArrayList<>();
		user.forEach((num) -> {
			UserVO dto = UserVO.builder()
					.userId(num.getUserId())
					.userEmail(num.getUserEmail())
					.userPw(num.getUserPw())
					.userName(num.getUserName())
					.roles(num.getRoles())
					.regDate(num.getRegDate())
					.build();
			users.add(dto);
		});
		return users;
	}

	/**
	   * Spring Security 필수 메소드 구현
	   *
	   * @param email 이메일
	   * @return UserDetails
	   * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
	   */
	  @Override // 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 User로 반환 타입 지정 (자동으로 다운 캐스팅됨)
	  public User loadUserByUsername(String id) throws UsernameNotFoundException {
		  return repository.findById(Long.parseLong(id)).orElseThrow(() -> new UsernameNotFoundException("NOT FOUND TOKEN"));
	  }

	
	





}
