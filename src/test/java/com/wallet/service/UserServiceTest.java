package com.wallet.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wallet.Entity.User;
import com.wallet.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {
	
	@MockBean // 'Mockar' o repository
	UserRepository repository;
	
	@Autowired
	UserService service;
	
	// esse method é a resposta mockada. INformando para o mockito o que a gente espera o que ele retorne.
	@BeforeEach
	public void setUP() {
		BDDMockito.given(repository.findByEmailEquals(Mockito.anyString())).willReturn(Optional.of(new User()));
	}
	
	// criando de fato o teste:
	@Test
	public void testFindByEmail() {
		Optional<User> user = service.findByEmail("email@teste.com");
		
		assertTrue(user.isPresent());
	}
}
