package com.wallet.service;

import com.wallet.entity.WalletItem;
import com.wallet.repository.WalletItemRepository;
import com.wallet.service.exception.BusinessException;
import com.wallet.util.enums.TypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class WalletItemService {
	
	@Autowired
	private WalletItemRepository repository;
	public WalletItem save(WalletItem wi) {

		
		return repository.save(wi);
	}

	public Page<WalletItem> findBetweenDates(Long wallet, Date start, Date end, int page, int size) {
		if (size < 1) {
			page = 1;
		}
		var pageRequest = PageRequest.of(page, size);
		
		return repository.findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(wallet, start, end, pageRequest);
	}

	public BigDecimal sumByWalletId(Long wallet) {

		return repository.sumByWalletId(wallet);
	}

	public List<WalletItem> findByWalletAndType(Long wallet, TypeEnum type) {
		// TODO Auto-generated method stub
		return repository.findByWalletIdAndType(wallet, type);
	}

	public WalletItem findById(Long id) {

		return repository.findById(id)
				.orElseThrow(() -> new BusinessException("WalletItem not found.", HttpStatus.NOT_FOUND.value()));
	}

	public void deleteById(Long id) {
		findById(id);
		repository.deleteById(id);
		
	}

}
