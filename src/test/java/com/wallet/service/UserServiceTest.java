package com.wallet.service;

import com.wallet.entity.User;
import com.wallet.repository.UserRepository;
import com.wallet.service.exception.BusinessException;
import com.wallet.util.enums.RoleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

	@Mock
	private UserRepository repository;
    @InjectMocks
    private UserService service;

//    @Mock
//    private PasswordEncoder passwordEncoder;

    @Test
    public void saveSuccess() {
        var user = getMockUser();
        when(repository.save(user)).thenReturn(user);

        var result = service.create(user);
        assertEquals(user, result);
    }

	@Test
	public void findByEmailSuccess() {
		var email = "email@teste.com";
		var user = new User();
		when(repository.findByEmailEquals(email)).thenReturn(Optional.of(user));

		var result = service.findByEmail(email);

		assertEquals(user, result);
	}

    @Test
    public void findByEmailWithNonExists() {
        when(repository.findByEmailEquals("TESTE@email.com")).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> {
            service.findByEmail("TESTE@email.com");
        });
    }


    public User getMockUser() {
        var user = new User();
        user.setId(1L);
        user.setPassword("$2a$12$Gz3kvnxNj535MG1E4UDwIu3zLLQ4tKpve/39SR/3UhmyzhblSgdrG\n");
        user.setName("TESTE");
        user.setEmail("TESTE@TESTE.COM");
        user.setRole(RoleEnum.ROLE_USER);

        return user;
    }
}
