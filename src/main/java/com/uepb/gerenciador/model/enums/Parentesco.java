package com.uepb.gerenciador.model.enums;

/**
 * Enumerador responsável por classificar o parentesco
 * @author Jose George e Caio
 *
 */

public enum Parentesco {

	AMIGO(1,"amigo"), CONHECIDO(2,"conhecido"), PRIMO(3,"primo"), PAI(4,"pai"), MAE(5,"mae"), FILHO(6,"filho"), NETO(7,"neto"), OUTRO(8,"outro");
	
	private int cod;
	private String descricao;
	
	private Parentesco(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Parentesco toEum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (Parentesco x : Parentesco.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido " + cod);
	}
	
}
