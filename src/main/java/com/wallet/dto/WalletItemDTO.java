package com.wallet.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class WalletItemDTO {

	private Long id;
	
	@NotNull(message = "Insira o ID da carteira.")
	private Long wallet;
	
	@NotNull(message = "Insira uma DATA")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date date;
	
	@NotNull(message = "Informe um tipo")
	//regexp = "^(ENTRADA|SAIDA)$": INdica que é uma expresão regular, realizando a validação do campo, aceitando apenas ENTRADA ou SAIDA.
	@Pattern(regexp = "^(ENTRADA|SAIDA)$", message = "Para o tipo somente são aceitos os valores ENTRADA ou SAÍDA")
	private String type;
	
	@NotNull(message = "Informe uma descrição")
	@Length(min = 5, message = "A descrição deve ter no mínimo 5 caracteres.")
	private String description;
	
	@NotNull(message = "Informe um valor")
	private BigDecimal value;
}
