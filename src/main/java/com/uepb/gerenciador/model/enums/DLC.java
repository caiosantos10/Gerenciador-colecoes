package com.uepb.gerenciador.model.enums;

/**
 * Enum para especificar os tipos de DLC para Jogos de VideoGames
 * @author Caio e Jose George
 *
 */
public enum DLC {
	LIVE(1, "live"), PSN(2, "psn"), STEAM(3, "steam"), DISCO(4, "disco");

	private int cod;
	private String descricao;

	private DLC(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static DLC toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (DLC x : DLC.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido " + cod);
	}
}
