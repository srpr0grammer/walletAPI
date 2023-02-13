package com.wallet.service;

import com.wallet.entity.User;
import com.wallet.entity.UserWallet;
import com.wallet.entity.Wallet;
import com.wallet.repository.UserWalletRepository;
import com.wallet.service.exception.BusinessException;
import com.wallet.util.enums.RoleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserWalletServiceTest {

    @Mock
    private UserWalletRepository userWalletRepository;

    @InjectMocks
	private UserWalletService userWalletService;

    @Test
    public void saveSuccess() {
        var userWallet = getMockerUserWallet();

        when(userWalletRepository.save(any(UserWallet.class))).thenReturn(userWallet);
        userWalletService.save(userWallet);

        verify(userWalletRepository).save(userWallet);

    }

    @Test
    public void findByUsersIdAndWalletIdSUcess() {
        var userId = getMockerUser().getId();
        var walletId = getMockerWallet().getId();
        var userWallet = getMockerUserWallet();

        when(userWalletRepository.findByUsersIdAndWalletId(userId, walletId)).thenReturn(Optional.of(userWallet));

        var result = userWalletService
                .findByUsersIdAndWalletId(getMockerUser().getId(), getMockerWallet().getId());

        assertEquals(1L, result.getId().longValue());
    }

    @Test
    public void findByUsersIdAndWalletIdSUcessNotFoundException() {
        var userId = getMockerUser().getId();
        var walletId = getMockerWallet().getId();

        when(userWalletRepository.findByUsersIdAndWalletId(userId, walletId)).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> {
            userWalletService.findByUsersIdAndWalletId(getMockerUser().getId(), getMockerWallet().getId());
        });
    }

    public User getMockerUser() {
        var user = new User();
        user.setId(1L);
        user.setPassword("$2a$12$Gz3kvnxNj535MG1E4UDwIu3zLLQ4tKpve/39SR/3UhmyzhblSgdrG\n");
        user.setName("TESTE");
        user.setEmail("TESTE@TESTE.COM");
        user.setRole(RoleEnum.ROLE_USER);

        return user;
    }

    public Wallet getMockerWallet() {
        var wallet = new Wallet();
        wallet.setId(1L);
        wallet.setName("TESTE");
        wallet.setValue(new BigDecimal("20.0"));

        return wallet;
    }

    public UserWallet getMockerUserWallet() {
        var userWallet = new UserWallet();
        userWallet.setId(1L);
        userWallet.setUsers(getMockerUser());
        userWallet.setWallet(getMockerWallet());

        return userWallet;
    }
}
