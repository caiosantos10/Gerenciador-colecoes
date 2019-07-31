package com.uepb.gerenciador.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class WishList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; 
	
	private String nomeDoProduto; 
	
	private Double preco; 
	
	private String urlProduto; 
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataLancamento; 
		
	public WishList(Integer id, String nomeDoProduto, Double preco, String urlProduto, Date dataLancamento,
			 Usuario usuario) {
		super();
		this.id = id;
		this.nomeDoProduto = nomeDoProduto;
		this.preco = preco;
		this.urlProduto = urlProduto;
		this.dataLancamento = dataLancamento;
		this.usuario = usuario;
	}

	@OneToOne
	private Usuario usuario; 
	
	public WishList() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeDoProduto() {
		return nomeDoProduto;
	}

	public void setNomeDoProduto(String nomeDoProduto) {
		this.nomeDoProduto = nomeDoProduto;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getUrlProduto() {
		return urlProduto;
	}

	public void setUrlProduto(String urlProduto) {
		this.urlProduto = urlProduto;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
