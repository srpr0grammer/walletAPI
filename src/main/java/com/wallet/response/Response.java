package com.wallet.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
A finlidade dessa classe é tratar toda as responstas da API.
TUdo que for devolvido sera tratado por esta classe
*/
@Getter
@Setter
@NoArgsConstructor // criando um construtor sem parametro para esta classe (Response)
public class Response <T> {
	
	// O atributo data sera de fato a resposta, que ira retornar, por ex uma carteira, um usuario ou qualquer outro tirpo de retorno noc aso de sucesso.
	private T data;
	
	// O atributo errors ira tratar erros.
	private List<String> errors;
	
	// INiciando um array vazio.
	public List<String> getErros(){
		if (this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		
		return errors;
	}
	
}
