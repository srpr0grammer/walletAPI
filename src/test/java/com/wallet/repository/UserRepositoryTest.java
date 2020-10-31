package com.wallet.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wallet.entity.User;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class UserRepositoryTest {

	private static final String EMAIL = "email@teste.com";
	
	@Autowired
	UserRepository repository;
	
	
	@BeforeEach // essa anotacao indica que esse metodo sera executado antes de qualquer outro metodo declartado
	public void setUp() {
		User u = new User();
		u.setName("Set up USer");
		u.setPassword("Senha123");
		u.setEmail(EMAIL);
		
		repository.save(u);
	}
	
	@AfterEach // essa anotacao indica que esse metodo sera excecutado depois que todos os metodos for executados.
	public void tearDown() {
		repository.deleteAll();
	}
	
	@Test
	public void testSave() {
	
		User u = new User();
		u.setName("Test");
		u.setPassword("123456");
		u.setEmail("teste@teste.com");
		
		
		User response = repository.save(u);
		
		assertNotNull(response);
		
	}
	
	public void testFindByEmail() {
		 Optional<User> response = repository.findByEmailEquals(EMAIL);
		
		 // validando se o usuario existe
		 assertTrue(response.isPresent());
		 
		 //validando se esse usuario que foi reotorno possui o mesmo email que salvamos
		 assertEquals(response.get().getEmail(), EMAIL);
		 
		
	}
}
