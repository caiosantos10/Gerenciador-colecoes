package com.uepb.gerenciador.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import com.uepb.gerenciador.model.enums.ItemEstado;

/**
 * <h1>Classe DVD_CD do Modelo</h1> 
 * @author José George e Caio Silva
 * @version 1.0.0
 * 		
 */

@Entity
public class DvdCd extends Item{
	
	/**
	 * DVD_CD extends ITEM, pois DVD_CD é do tipo Item
	 * */
	
	private static final long serialVersionUID = 1L;
	
	private Integer estado; 
	
	@NotBlank(message="campo obrigatório")
	private String conteudo; 
	
	private String marca; 
	
	/**Se o filme já foi assistido ou não*/
	private boolean statusDeUso;
	
	public DvdCd() {}

	public DvdCd(ItemEstado estado, String conteudo, String marca, boolean statusDeUso) {
		super();
		this.estado = estado.getCod();
		this.conteudo = conteudo;
		this.marca = marca;
		this.statusDeUso = statusDeUso;
	}
	
	public ItemEstado getEstado() {
		return ItemEstado.toEnum(estado);
	}

	public void setEstado(ItemEstado estado) {
		this.estado = estado.getCod();
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public boolean isStatusDeUso() {
		return statusDeUso;
	}

	public void setStatusDeUso(boolean statusDeUso) {
		this.statusDeUso = statusDeUso;
	}
	
}
