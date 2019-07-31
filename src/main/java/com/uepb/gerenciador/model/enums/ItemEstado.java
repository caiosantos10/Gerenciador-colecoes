package com.uepb.gerenciador.model.enums;

/**
 * Enumerado responsável por classficar os itens como novos ou usados
 * @author Jose George e Caio
 *
 */

public enum ItemEstado {

	NOVO(1, "novo"), RASURADO(2, "rasurado");

	private int cod;
	private String descricao;

	private ItemEstado(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static ItemEstado toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (ItemEstado x : ItemEstado.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido " + cod);
	}

}
