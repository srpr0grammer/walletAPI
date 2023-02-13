package com.wallet.service;

import com.wallet.entity.Wallet;
import com.wallet.entity.WalletItem;
import com.wallet.repository.WalletItemRepository;
import com.wallet.service.exception.BusinessException;
import com.wallet.util.enums.TypeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class WalletItemServiceTest {

	@Mock
	private WalletItemRepository repository;

	@InjectMocks
	private WalletItemService service;

    @Test
	public void saveSuccess() {
		var walletItem = getMockWalletItem();

        when(repository.save(any(WalletItem.class))).thenReturn(walletItem);
       	service.save(walletItem);

        verify(repository).save(walletItem);
	}

	@Test
	public void getByIdSuccess() {
		var walletItem = getMockWalletItem();

		when(repository.findById(1L)).thenReturn(Optional.of(walletItem));
		var actualWalletItem = service.findById(1L);

		assertEquals(walletItem, actualWalletItem);
	}

	@Test
	public void getByIdWithNonExistsId() {
		when(repository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(BusinessException.class, () -> {
			service.findById(1L);
		});

	}

	@Test
	public void findBetweenDatesSuccess() {
		var items = List.of(getMockWalletItem());

		when(repository.findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual
				(anyLong(), any(Date.class), any(Date.class), any(PageRequest.class))).thenReturn(new PageImpl<>(items));

		var result = service
				.findBetweenDates(1L, new Date(), new Date(), 1, 1);

		assertNotNull(result);
		assertEquals(1, result.getSize());

	}

	@Test
	public void findByWalletAndTypeSuccess() {
		var expectedItems = List.of(getMockWalletItem());

		when(repository.findByWalletIdAndType(anyLong(), any(TypeEnum.class))).thenReturn(expectedItems);

		var result =
				service.findByWalletAndType(1L, TypeEnum.EN);

		verify(repository).findByWalletIdAndType(1L, TypeEnum.EN);
		assertEquals(expectedItems, result);

	}

	@Test
	public void deleteSuccess() {
		var expectedWalletItem = getMockWalletItem();

		when(repository.findById(1L)).thenReturn(Optional.of(expectedWalletItem));
		service.deleteById(expectedWalletItem.getId());

		verify(repository).deleteById(expectedWalletItem.getId());
	}

	@Test
	public void deleteWithInvalidId() {
		Long id = null;
		try {
			service.deleteById(id);
			fail("WalletItem not found.");
		} catch (BusinessException e) {

		}

		verify( repository, never()).deleteById(id);
	}

	@Test
	public void deleteWithNonExistsId() {

		when(repository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(BusinessException.class, () -> {
			service.findById(1L);
		});
	}

    private Wallet getMockWallet() {
        var wallet = new Wallet();
        wallet.setId(1L);
        wallet.setName("TESTE");
        wallet.setValue(new BigDecimal("20.30"));

        return wallet;
    }

	private WalletItem getMockWalletItem() {
		var walletItem = new WalletItem();
		var date = new Date();

		walletItem.setId(1L);
        walletItem.setValue(new BigDecimal("20.30"));
        walletItem.setWallet(getMockWallet());
        walletItem.setDate(date);
		walletItem.setType(TypeEnum.EN);

		return walletItem;
	}

}
