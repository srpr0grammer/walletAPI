package com.wallet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.dto.UserDTO;
import com.wallet.dto.WalletDTO;
import com.wallet.entity.User;
import com.wallet.entity.Wallet;
import com.wallet.service.UserService;
import com.wallet.service.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class WalletControllerTest {
	private static final String URL = "/wallet";

	@Mock
    private WalletService walletService;

    @Mock
    private ModelMapper modelMapper;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void saveSuccess() throws Exception {
        var walletDTO = getMockWalletDTO();
        var wallet = modelMapper.map(walletDTO, Wallet.class);

        when(walletService.save(any(Wallet.class))).thenReturn(wallet);
        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(walletDTO)))
                .andExpect(status().isCreated());

	}

    private WalletDTO getMockWalletDTO() {
        var dto = new WalletDTO();
        dto.setId(1L);
        dto.setName("TESTE");
        dto.setValue(new BigDecimal("20.1"));

        return dto;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
