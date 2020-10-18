package com.wallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.Entity.UserWallet;
import com.wallet.repository.UserWalletRepository;
import com.wallet.service.UserWalletService;

@Service
public class UserWalletServiceImpl implements UserWalletService{
	
	@Autowired
	private UserWalletRepository repository;
	
	@Override
	public UserWallet save(UserWallet uw) {

		return repository.save(uw);
	}

}
