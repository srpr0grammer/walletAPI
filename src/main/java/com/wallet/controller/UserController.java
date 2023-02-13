package com.wallet.controller;

import com.wallet.dto.UserDTO;
import com.wallet.entity.User;
import com.wallet.response.UserResponse;
import com.wallet.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;

	@Autowired
	private ModelMapper modelMapper;
	@PostMapping
	public ResponseEntity<UserResponse> create(@Valid @RequestBody UserDTO userDTO){
		var user = service.create(modelMapper.map(userDTO, User.class));

		return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(user, UserResponse.class));
	}
}
