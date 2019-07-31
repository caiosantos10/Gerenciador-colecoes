package com.uepb.gerenciador.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uepb.gerenciador.model.enums.Parentesco;
import com.uepb.gerenciador.model.enums.Sexo;

/**
 * <h1>Classe Amigo do Modelo</h1> 
 * @author José George e Caio Silva
 * @version 1.0.0
 * 	Representa os amigos referentes aos usuarios do sistema
 */

@Entity
public class Amigo extends Pessoa{
	
	private static final long serialVersionUID = 1L;
	
	private Integer sexo;
	private Integer parentesco;
		
	@JsonIgnore 
	@OneToMany(mappedBy="amigo")
	private List<Endereco> enderecos = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>();

	@OneToOne
	private Usuario usuario;
	
	public Amigo() {
	}

	public Amigo(Integer sexo, Integer parentesco,Usuario usuario) {
		super();
		this.sexo = sexo;
		this.parentesco = parentesco;
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Sexo getSexo() {
		return Sexo.toEum(sexo);
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo.getCod();
	}

	public Parentesco getParentesco() {
		return Parentesco.toEum(parentesco);
	}
	
	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco.getCod();
	}

	/**
	 *@return lista de endereços, pois um usuário pode ter um ou vários endereços
	 */
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	/**
	 * @return lista de telefones, pois um usuário pode ter um ou vários telefones
	 */
	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	@Override
	public String toString() {
		return super.getNome();
	}
}
