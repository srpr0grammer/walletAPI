package com.wallet.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class UserDTO {

	private Long id;
	
	@Email(message = "Email inválido.")
	private String email;
	
	@Length(message = "O nome deve conter entre 3 e 50 caracteres.")
	private String name;
	
	@NotNull()
	@Length(min = 6, message = " A senha deve conter no minimo 6 caractere.")
	private String password;
}
