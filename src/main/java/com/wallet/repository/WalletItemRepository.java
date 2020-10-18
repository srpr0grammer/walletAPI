package com.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.Entity.WalletItem;

public interface WalletItemRepository extends JpaRepository<WalletItem, Long>{

	
}
