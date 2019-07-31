package com.uepb.gerenciador.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * <h1>Classe Emprestimo do Modelo</h1> 
 * @author José George e Caio Silva
 * @version 1.0.0
 * 		
 * Classe que conhece Item que foi emprestado, datas de emprestimo e devolucao, 
 *  como tambem Amigo a quem foi emprestado .
 */

@Entity
public class Emprestimo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; 
	
	@OneToOne
	private Item item; 

	@OneToOne
	private Amigo amigo; 
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataDeEmprestimo; 
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataDeDevolucao;
	
	@OneToOne
	private Usuario usuario;
	
	
	/* Formato para exbicao das datas de emprestimo e devolucao*/
	
	
	public Emprestimo() {
		
	}
	
	public Emprestimo (Item item, Amigo amigo, Date dataDeEmprestimo, 
			Date dataDeDevolucao, Usuario usuario) {
		super();
		this.item = item;
		this.amigo = amigo;
		this.dataDeEmprestimo = dataDeEmprestimo;
		this.dataDeDevolucao = dataDeDevolucao;
		this.usuario = usuario;
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

	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		item.setNumEmprestimos();
		this.item = item;
		
	}

	public Amigo getAmigo() {
		return amigo;
	}

	public void setAmigo(Amigo amigo) {
		this.amigo = amigo;
	}

	public Date getDataDeEmprestimo() {
		return dataDeEmprestimo;
	}

	public void setDataDeEmprestimo(String dataDeEmprestimo) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		this.dataDeEmprestimo = formato.parse(dataDeEmprestimo);
	}
	
	/*
	 * <p>Sobrecarga do metodo setDataEmprestimo usado no service para update de emprestimo</p>
	 * */
	public void setDataDeEmprestimo(Date dataDeEmprestimo) {
		this.dataDeEmprestimo = dataDeEmprestimo;
	}

	public Date getDataDeDevolucao() {
		return dataDeDevolucao;
	}

	public void setDataDeDevolucao(String dataDeDevolucao) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		this.dataDeDevolucao = formato.parse(dataDeDevolucao);
	}
	
	/*
	 * <p>Sobrecarga do metodo setDataDevolucao usado no service para update de emprestimo</p>
	 * */
	public void setDataDeDevolucao(Date dataDeDevolucao) {
		this.dataDeDevolucao = dataDeDevolucao;
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
		Emprestimo other = (Emprestimo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}
