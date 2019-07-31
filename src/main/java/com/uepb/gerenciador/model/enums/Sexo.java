package com.uepb.gerenciador.model.enums;

/**
 * Enumerador responsável por classificar o sexo
 * @author Jose George e Caio
 *
 */

public enum Sexo {

	MASCULINO(1, "M"), FEMININO(2,"F");
	
	private int cod;
	private String descricao;
	
	private Sexo(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Sexo toEum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (Sexo x : Sexo.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido " + cod);
	}
	
}
