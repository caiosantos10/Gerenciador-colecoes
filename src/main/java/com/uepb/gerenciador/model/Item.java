package com.uepb.gerenciador.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

/**
 * <h1>Classe Item do Modelo</h1> 
 * @author José George e Caio Silva
 * @version 1.0.0
 * 		
 * Super classe que serve como base para todos os itens de modelo
 */

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; 
	
	@NotBlank(message = "Titulo é obrigatório !")
	private String titulo; 
	
	private Double preco; 
	private String observacoes;
	private boolean isImportant = false;
	private Integer numEmprestimos = 0;
	private boolean isEmprestado = false;
	
	@OneToOne
	private Usuario usuario;
	
	public Item() {}

	public Item(String titulo, Double preco, String observacoes, boolean isImportant, Usuario usuario, boolean IsEmprestado) {
		super();
		this.titulo = titulo;
		this.preco = preco;
		this.observacoes = observacoes;
		this.isImportant = isImportant;
		this.usuario = usuario;
		this.isEmprestado = IsEmprestado;
	}
		
	public boolean isEmprestado() {
		return isEmprestado;
	}

	public void setEmprestado(boolean isEmprestado) {
		this.isEmprestado = isEmprestado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getObservacoes() {
		return observacoes;
	}
	
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean isImportant() {
		return isImportant;
	}

	public void setImportant(boolean isImportant) {
		this.isImportant = isImportant;
	}

	public Integer getNumEmprestimos() {
		return numEmprestimos;
	}

	public void setNumEmprestimos() {
		this.numEmprestimos++;
	}

	@Override
	public String toString() {
		return getTitulo();
	}
	
	/**
	 * Compara itens pelo titulo
	 * @param item item a ser comparado
	 * @return valor boleano indicando se titulos sao iguais ou nao
	 */
	public boolean isEqual(Item item) {
		if (this.titulo.equals(item.titulo))
			return true;
		return false;
	}

	
}
