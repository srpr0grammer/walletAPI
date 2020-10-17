package com.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.Entity.User;
import com.wallet.dto.UserDTO;
import com.wallet.service.UserService;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	private static final Long ID = 1L;
	private static final String EMAIL = "email@teste.com";
	private static final String NAME = "UsuarioTest";
	private static final String PASSWORD = "123456";
	private static final String URL = "/user";

	@MockBean
	UserService service;
	
	@Autowired
	MockMvc mvc;
	
	@Test
	public void testSave() throws Exception {
	
		BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockUser());
		mvc.perform(MockMvcRequestBuilders.post(URL).content(getJesonPayload(ID, EMAIL, NAME, PASSWORD))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.data.id").value(ID))
				.andExpect(jsonPath("$.data.email").value(EMAIL))
				.andExpect(jsonPath("$.data.name").value(NAME))
				.andExpect(jsonPath("$.data.password").doesNotExist());
				
	}
	
	public void testSaveInvalidUSer () throws JsonProcessingException, Exception {
		mvc.perform(MockMvcRequestBuilders.post(URL).content(getJesonPayload(ID, "Teste", NAME, PASSWORD))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.erros[0]").value("Email inválido!"));
	} 
	
	public User getMockUser() {
		User user = new User();
		user.setId(ID);
		user.setEmail(EMAIL);
		user.setName(NAME);
		user.setPassword(PASSWORD);
		
	return user;
	}
	
	public String getJesonPayload(Long id, String email, String name, String password) throws JsonProcessingException {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setEmail(email);
		dto.setName(name);
		dto.setPassword(password);
		
		// Utiizando MApper para fazer a conversão do objeto DTO para uma String no formato JSON
		ObjectMapper mapper = new ObjectMapper();
		
		// realizando de fato a conversão do DTO
		return mapper.writeValueAsString(dto);
		
		
	}
}
