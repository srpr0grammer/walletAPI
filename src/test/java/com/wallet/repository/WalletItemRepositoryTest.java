package com.wallet.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wallet.Entity.Wallet;
import com.wallet.Entity.WalletItem;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class WalletItemRepositoryTest {
	
	//Declarnado as constante da calsse WAlletItem para teste.
	private static final Date DATE = new Date(0);
	private static final String TYPE = "EN";
	private static final String DESCRIPTION = "Conta de LUz";
	private static final BigDecimal VALUE = BigDecimal.valueOf(65);

	@Autowired
	WalletItemRepository repository;
	
	@Autowired
	WalletRepository walletRepository;
	
	@Test
	public void testSave() {
		//1. Estaciando o objeto Wallet.
		Wallet w = new Wallet();
		w.setName("Carteira 1");
		w.setValue(BigDecimal.valueOf(500));
		walletRepository.save(w);
		
		//2. Estanciando de fato o WAlletItem
		WalletItem wi = new WalletItem(1L, w, DATE, TYPE, DESCRIPTION, VALUE);
		
		WalletItem response =  repository.save(wi);
		
		assertNotNull(response);
		assertEquals(response.getDescription(), DESCRIPTION);
		assertEquals(response.getType(), TYPE);
		assertEquals(response.getValue(), VALUE);
		assertEquals(response.getWallet().getId(), w.getId());
	}
}
