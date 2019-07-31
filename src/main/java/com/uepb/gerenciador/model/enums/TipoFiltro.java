package com.uepb.gerenciador.model.enums;

public enum TipoFiltro {
	TITULO(1, "Titulo"), MARCA(2, "Marca"), EDITORA(3, "Editora"), UNIVERSO(4, "Universo"), CONSOLE(5, "Console"); 

	private int cod;
	private String descricao;

	private TipoFiltro(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoFiltro toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (TipoFiltro x : TipoFiltro.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido " + cod);
	}

}
