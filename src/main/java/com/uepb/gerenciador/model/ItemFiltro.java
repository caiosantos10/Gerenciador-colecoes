package com.uepb.gerenciador.model;

import com.uepb.gerenciador.model.enums.TipoFiltro;

public class ItemFiltro {
	
	private Integer tipoFiltro;
	private String filtro;
	
	public TipoFiltro getTipoFiltro() {
		return TipoFiltro.toEnum(tipoFiltro);
	}
	public void setTipoFiltro(TipoFiltro tipoFiltro) {
		this.tipoFiltro = tipoFiltro.getCod();
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
