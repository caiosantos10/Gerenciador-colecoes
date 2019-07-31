package com.uepb.gerenciador.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import com.uepb.gerenciador.model.enums.Perfil;

/**
 * <h1>Classe que representa os usuarios do sistema</h1> 
 * @author Caio
 *
 */
@Entity
public class Usuario extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	private String email; 
	
	private String senha;

	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	public Usuario() {
		addPerfil(Perfil.USER);
	}

	public Usuario(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
		addPerfil(Perfil.USER);
	}

	public Set<Perfil> getPerfis(){
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}	
	
}
