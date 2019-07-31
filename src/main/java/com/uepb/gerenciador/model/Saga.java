package com.uepb.gerenciador.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * <h1>Classe Saga do Modelo</h1> 
 * @author José George e Caio Silva
 * @version 1.0.0
 * 		
 */

@Entity
public class Saga {
	
	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;
	
	private String nomeSaga;
	
	private Integer numeroSaga;
	
	public String getNomeSaga() {
		return nomeSaga;
	}
	
	public void setNomeSaga(String nomeSaga) {
		this.nomeSaga = nomeSaga;
	}
	
	public Integer getNumeroSaga() {
		return numeroSaga;
	}
	
	public void setNumeroSaga(Integer numeroSaga) {
		this.numeroSaga = numeroSaga;
	}
	
	@Override
	public String toString() {
		return this.nomeSaga + " #" + this.numeroSaga;
	}
}
