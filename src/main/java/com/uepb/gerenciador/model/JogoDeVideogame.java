package com.uepb.gerenciador.model;

import javax.persistence.Entity;

import com.uepb.gerenciador.model.enums.DLC;
import com.uepb.gerenciador.model.enums.ItemEstado;

/**
 * <h1>Classe JogoDeVideogame do Modelo</h1> 
 * @author José George e Caio Silva
 * @version 1.0.0
 * 		
 */

@Entity
public class JogoDeVideogame extends Item{

	/**
	 * JogoDeTabuleiro extends ITEM, pois JogoDeTabuleiro é do tipo Item
	 * */
	
	private static final long serialVersionUID = 1L;

	private boolean statusJogo;
	private String console; 
	private Integer estado; 
	private Integer dlc;
	private boolean possuiDLC;	
	
	public JogoDeVideogame() {}

	
	public JogoDeVideogame(boolean statusJogo, String console, Integer estado, Integer dlc) {
		super();
		this.statusJogo = statusJogo;
		this.console = console;
		this.estado = estado;
		this.dlc = dlc;
		this.verificaDLC(dlc);
	}

	public ItemEstado getEstado() {
		return ItemEstado.toEnum(estado);
	}

	public void setEstado(ItemEstado estado) {
		this.estado = estado.getCod();
	}

	public boolean isStatusJogo() {
		return statusJogo;
	}

	public void setStatusJogo(boolean statusJogo) {
		this.statusJogo = statusJogo;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public boolean isPossuiDLC() {
		return possuiDLC;
	}

	public void setPossuiDLC(boolean possuiDLC) {
		this.possuiDLC = possuiDLC;
	}

	public DLC getDlc() {
		return DLC.toEnum(dlc);
	}

	public void setDlc(DLC dlc) {
		this.dlc = dlc.getCod();
	}
	
	private void verificaDLC(Integer dlc) {
		if (dlc.equals(null))
			this.possuiDLC = false;
		else
			this.possuiDLC = true;
	}
	
}
