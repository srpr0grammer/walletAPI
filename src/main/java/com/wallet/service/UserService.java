package com.wallet.service;

import com.wallet.entity.User;
import com.wallet.repository.UserRepository;
import com.wallet.service.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User create(User user) {
//		user.setEmail(getPasswordEncode(user.getPassword()));

		return repository.save(user);
	}

	public User findByEmail(String email) {

		return repository.findByEmailEquals(email).orElseThrow(() -> new BusinessException("E-mail not found.", HttpStatus.NOT_FOUND.value()));
	}
//	public String getPasswordEncode(String password) {
//		var encode = new BCryptPasswordEncoder();
//		return encode.encode(password);
//	}
	
}
