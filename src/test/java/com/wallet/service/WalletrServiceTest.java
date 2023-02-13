package com.wallet.service;

import com.wallet.entity.Wallet;
import com.wallet.repository.WalletRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class WalletrServiceTest {

	@Mock // 'Mockar' o repository
	private WalletRepository walletRepository;

    @InjectMocks
    private WalletService walletService;

//    @Mock
//    private PasswordEncoder passwordEncoder;


    @Test
    public void saveSuccess() {
        var wallet = getMockWallet();
        when(walletRepository.save(wallet)).thenReturn(wallet);

        walletService.save(wallet);

        verify(walletRepository).save(wallet);
    }

    public Wallet getMockWallet() {
        var wallet = new Wallet();
        wallet.setId(1L);
        wallet.setName("TESTE");
        wallet.setValue(new BigDecimal("1.0"));

        return wallet;
    }
}
