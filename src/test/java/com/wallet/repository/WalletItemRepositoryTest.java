//package com.wallet.repository;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.math.BigDecimal;
//import java.sql.Date;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.data.domain.Page;
//
//import com.wallet.entity.Wallet;
//import com.wallet.entity.WalletItem;
//import com.wallet.util.enums.TypeEnum;
//
//@ExtendWith(SpringExtension.class)
//@ActiveProfiles("test")
//@SpringBootTest
//public class WalletItemRepositoryTest {
//
//	// Declarnado as constante da calsse WAlletItem para teste.
//	private static final Date DATE = new Date(0);
//	private static final TypeEnum TYPE = TypeEnum.EN;
//	private static final String DESCRIPTION = "Conta de LUz";
//	private static final BigDecimal VALUE = BigDecimal.valueOf(65);
//	private Long savedWalletItemId = null;
//	private Long savedWalletId = null;
//
//	@Autowired
//	WalletItemRepository repository;
//
//	@Autowired
//	WalletRepository walletRepository;
//
//	@BeforeEach
//	public void setUp() {
//		Wallet w = new Wallet();
//		w.setName("Carteria Test");
//		w.setValue(BigDecimal.valueOf(250));
//		walletRepository.save(w);
//
//		WalletItem wi = new WalletItem(null, w, DATE, TYPE, DESCRIPTION, VALUE);
//		repository.save(wi);
//
//		savedWalletItemId = wi.getId();
//		savedWalletId = wi.getId();
//
//	}
//
//	@AfterEach
//	public void tearDown() {
//		repository.deleteAll();
//		walletRepository.deleteAll();
//	}
//
//	@Test
//	public void testSave() {
//		// 1. Estaciando o objeto Wallet.
//		Wallet w = new Wallet();
//		w.setName("Carteira 1");
//		w.setValue(BigDecimal.valueOf(500));
//		walletRepository.save(w);
//
//		// 2. Estanciando de fato o WAlletItem
//		WalletItem wi = new WalletItem(1L, w, DATE, TYPE, DESCRIPTION, VALUE);
//
//		WalletItem response = repository.save(wi);
//
//		assertNotNull(response);
//		assertEquals(response.getDescription(), DESCRIPTION);
//		assertEquals(response.getType(), TYPE);
//		assertEquals(response.getValue(), VALUE);
//		assertEquals(response.getWallet().getId(), w.getId());
//	}
//
//	@Test
//	public void testSaveInvalidWalletItem() {
//		WalletItem wi = new WalletItem(null, null, DATE, null, DESCRIPTION, null);
//	//	repository.save(w112i);
//	}
//
//	@Test
//	public void testUpdate() {
//		Optional<WalletItem> wi = repository.findById(savedWalletItemId);
//
//		String description = "Descrucao alterada";
//
//		WalletItem changed = wi.get();
//		changed.setDescription(description);
//
//		repository.save(changed);
//
//		Optional<WalletItem> newWalletItem = repository.findById(savedWalletItemId);
//
//		assertEquals(description, newWalletItem.get().getDescription());
//	}
//
//	@Test
//	public void deleteWalletItem() {
//		Optional<Wallet> wallet = walletRepository.findById(savedWalletId);
//		WalletItem wi = new WalletItem(null, wallet.get(), DATE, TYPE, DESCRIPTION, VALUE);
//
//		repository.save(wi);
//
//		repository.deleteById(wi.getId());
//
//		Optional<WalletItem> response = repository.findById(wi.getId());
//
//		assertFalse(response.isPresent());
//	}
//
//	@Test
//	public void testFindBetweenDates() {
//		Optional<Wallet> w = walletRepository.findById(savedWalletId);
//
//		LocalDateTime localDateTime = DATE.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//
//		Date currentDatePlusFiveDays = (Date) Date.from(localDateTime.plusDays(5).atZone(ZoneId.systemDefault()).toInstant());
//		Date currentDatePlusSevenDays = (Date) Date.from(localDateTime.plusDays(7).atZone(ZoneId.systemDefault()).toInstant());
//
//		repository.save(new WalletItem(null, w.get(), currentDatePlusFiveDays, TYPE, DESCRIPTION, VALUE));
//		repository.save(new WalletItem(null, w.get(), currentDatePlusSevenDays, TYPE, DESCRIPTION, VALUE));
//
//		PageRequest pg = PageRequest.of(0, 10);
//		Page<WalletItem> response = repository.findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(savedWalletId, DATE, currentDatePlusFiveDays, pg);
//
//		assertEquals(response.getContent().size(), 2);
//		assertEquals(response.getTotalElements(), 2);
//		assertEquals(response.getContent().get(0).getWallet(), savedWalletId);
//	}
//
//	@Test
//	public void testFindByType() {
//		List<WalletItem> response = repository.findByWalletIdAndType(savedWalletId, TYPE);
//
//		assertEquals(response.size(), 1);
//		assertEquals(response.get(0).getType(), TYPE);
//	}
//
//	@Test
//	public void testFindByTypeSd() {
//		Optional<Wallet> w = walletRepository.findById(savedWalletId);
//
//		repository.save(new WalletItem(null, w.get(), DATE, TYPE, DESCRIPTION, VALUE));
//
//		List<WalletItem> response = repository.findByWalletIdAndType(savedWalletId, TypeEnum.SD);
//
//		assertEquals(response.size(), 1);
//		assertEquals(response.get(0).getType(), TypeEnum.SD);
//	}
//	@Test
//	public void testSumByWallet() {
//		Optional<Wallet> w = walletRepository.findById(savedWalletId);
//
//		repository.save(new WalletItem(null, w.get(), DATE, TYPE, DESCRIPTION, BigDecimal.valueOf(150.80)));
//
//		BigDecimal response = repository.sumByWalletId(savedWalletId);
//
//		assertEquals(response.compareTo(BigDecimal.valueOf(215.8)), 0);
//	}
//}
