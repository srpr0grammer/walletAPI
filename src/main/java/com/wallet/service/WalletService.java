package com.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.entity.Wallet;
import com.wallet.repository.WalletRepository;

@Service
public class WalletService {
	
	@Autowired
	private WalletRepository repository;
	
	public Wallet save(Wallet wallet) {

		return repository.save(wallet);
	}
	
}
