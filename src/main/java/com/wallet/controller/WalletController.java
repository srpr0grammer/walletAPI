package com.wallet.controller;

import javax.validation.Valid;

import com.wallet.service.WalletService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.dto.WalletDTO;
import com.wallet.entity.Wallet;
import com.wallet.response.Response;

@RestController
@RequestMapping("/wallet")
public class WalletController {
	
	@Autowired
	private WalletService service;

	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<WalletDTO> create(@Valid @RequestBody WalletDTO walletDTO){
		var wallet = modelMapper.map(walletDTO, Wallet.class);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(modelMapper.map(wallet, WalletDTO.class));
	}

}
