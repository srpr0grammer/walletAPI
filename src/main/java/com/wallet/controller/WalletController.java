package com.wallet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.Entity.Wallet;
import com.wallet.dto.WalletDTO;
import com.wallet.response.Response;
import com.wallet.service.WalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController {
	
	@Autowired
	private WalletService service;
	
	@PostMapping
	public ResponseEntity<Response<WalletDTO>> create(@Valid @RequestBody WalletDTO walletDTO, BindingResult result){
		 
		Response<WalletDTO> response = new Response<WalletDTO>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
		
			return ResponseEntity.badRequest().body(response);
		}
		
		Wallet wallet = service.save(this.converterDtoToEntity(walletDTO));
		
		response.setData(this.converterEntityToDto(wallet));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
	
	private Wallet converterDtoToEntity(WalletDTO dto) {
		Wallet wallet = new Wallet();
		wallet.setId(dto.getId());
		wallet.setName(dto.getName());
		wallet.setValue(dto.getValue());
		
		return wallet;
	}
	
	private WalletDTO converterEntityToDto(Wallet wallet) {
		WalletDTO walletDTO = new WalletDTO();
		walletDTO.setId(wallet.getId());
		walletDTO.setName(wallet.getName());
		walletDTO.setValue(wallet.getValue());	
	
		return walletDTO;
	}
}
