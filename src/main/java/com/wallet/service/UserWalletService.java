package com.wallet.service;

import com.wallet.entity.UserWallet;
import com.wallet.repository.UserWalletRepository;
import com.wallet.service.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserWalletService{
	
	@Autowired
	private UserWalletRepository repository;
	
	public UserWallet save(UserWallet userWallet) {

		return repository.save(userWallet);
	}

	public UserWallet findByUsersIdAndWalletId(Long user, Long wallet) {


		return repository.findByUsersIdAndWalletId(user, wallet)
				.orElseThrow(() -> new BusinessException("This data not found in data base.", HttpStatus.NOT_FOUND.value()));
	}

}
