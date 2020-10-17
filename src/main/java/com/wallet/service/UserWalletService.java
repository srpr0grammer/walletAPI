package com.wallet.service;

import com.wallet.Entity.User;
import com.wallet.Entity.UserWallet;
import com.wallet.Entity.Wallet;

public interface UserWalletService {

	UserWallet save(UserWallet userWallet,  Wallet wallet, User user);
}
