package com.uepb.gerenciador.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.uepb.gerenciador.model.enums.ItemEstado;

/**
 * <h1>Classe HQ do Modelo</h1> 
 * @author José George e Caio Silva
 * @version 1.0.0
 * 		
 */

@Entity
public class HQ extends Item{

	/**
	 * HQ extends ITEM, pois HQ é do tipo Item
	 * */
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn
	private Saga saga;
	
	private String editora; 	
	private String universo;	
	private Integer estado; 	
	private boolean statusLeitura; 
	
	public HQ() {}
	
	public HQ(Saga saga, String editora, String universo, ItemEstado estado,
			boolean statusLeitura) {
		super();
		this.saga = saga;
		this.editora = editora;
		this.universo = universo;
		this.estado = estado.getCod();
		this.statusLeitura = statusLeitura;
	}
	
	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getUniverso() {
		return universo;
	}

	public void setUniverso(String universo) {
		this.universo = universo;
	}

	public ItemEstado getEstado() {
		return ItemEstado.toEnum(estado);
	}

	public void setEstado(ItemEstado estado) {
		this.estado = estado.getCod();
	}

	public boolean isStatusLeitura() {
		return statusLeitura;
	}

	public void setStatusLeitura(boolean statusLeitura) {
		this.statusLeitura = statusLeitura;
	}

	public Saga getSaga() {
		return saga;
	}

	public void setSaga(Saga saga) {
		this.saga = saga;
	}
		
}
