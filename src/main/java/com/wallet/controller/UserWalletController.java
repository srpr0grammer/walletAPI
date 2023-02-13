package com.wallet.controller;

import javax.validation.Valid;

import com.wallet.service.UserWalletService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.dto.UserWalletDTO;
import com.wallet.entity.User;
import com.wallet.entity.UserWallet;
import com.wallet.entity.Wallet;
import com.wallet.response.Response;

@RestController
@RequestMapping("/user-wallet")
public class UserWalletController {

	@Autowired
	private UserWalletService service;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<UserWalletDTO> create (@Valid @RequestBody UserWalletDTO dto, BindingResult result){
		var userWallet = modelMapper.map(dto, UserWallet.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(userWallet, UserWalletDTO.class));
	}

}
