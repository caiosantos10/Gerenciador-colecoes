package com.uepb.gerenciador.model;

import javax.persistence.Entity;

import com.uepb.gerenciador.model.enums.ItemEstado;

/**
 * <h1>Classe JogoDeTabuleiro do Modelo</h1> 
 * @author José George e Caio Silva
 * @version 1.0.0
 * 		
 */

@Entity
public class JogoDeTabuleiro extends Item{

	/**
	 * JogoDeTabuleiro extends ITEM, pois JogoDeTabuleiro é do tipo Item
	 * */
	
	private static final long serialVersionUID = 1L;
	private Integer estado; 
	
	public JogoDeTabuleiro() {}
	
	public JogoDeTabuleiro(Integer estado) {
		super();
		this.estado = estado;
	}

	public ItemEstado getEstado() {
		return ItemEstado.toEnum(estado);
	}

	public void setEstado(ItemEstado estado) {
		this.estado = estado.getCod();
	}
	
	
}
