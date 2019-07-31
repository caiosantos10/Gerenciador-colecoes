package com.uepb.gerenciador.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uepb.gerenciador.exception.ItemInexistenteException;
import com.uepb.gerenciador.exception.ObjectNotFoundException;
import com.uepb.gerenciador.model.Amigo;
import com.uepb.gerenciador.model.Endereco;
import com.uepb.gerenciador.model.enums.Parentesco;
import com.uepb.gerenciador.model.enums.Sexo;
import com.uepb.gerenciador.service.impl.AmigoServiceImpl;
import com.uepb.gerenciador.service.impl.EnderecoServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmigoTest {

	@Autowired
	private AmigoServiceImpl amigoService;
	
	@Autowired
	private EnderecoServiceImpl enderecoService;
	
	private Amigo amigo; 
	private Endereco endereco;
	private List <Endereco> enderecos;
	private Set<String> telefones;
	
	@Before
	public void setUp() {
		amigo = new Amigo();
		endereco = new Endereco();
		enderecos  = new ArrayList <Endereco>();
		telefones = new HashSet<String>();
		
		telefones.add("3354-1100");
		
		endereco.setCep("58580-000");
		endereco.setLogradouro("Rua joao Bezerra de Souza");
		endereco.setNumero("38");
		enderecoService.create(endereco);
		
		enderecos.add(endereco);
		
		amigo.setNome("Joao Jose");
		amigo.setUsuario(null);
		amigo.setSexo(Sexo.MASCULINO);
		amigo.setParentesco(Parentesco.AMIGO);
		amigo.setEnderecos(enderecos);
		amigo.setTelefones(telefones);
		amigoService.create(amigo);
		
	}
	
	@Test
	public void buscaPorIdTest() {
		
		assertEquals(amigo, amigoService.getById(amigo.getId()));
	}
	
	@Test (expected = ObjectNotFoundException.class)
	public void buscaPorIdInexistenteTest() {
		amigoService.getById(1234567); 
	}
	
	@Test 
	public void updateTest() {
		Amigo novoAmigo = new Amigo();
		novoAmigo.setNome("Maria");
		novoAmigo.setSexo(Sexo.MASCULINO);
		
		Amigo updatedAmigo = amigoService.update(amigo.getId(), novoAmigo);
		
		assertEquals(novoAmigo.getNome(), updatedAmigo.getNome());
		
	}
	
	@Test
	public void deleteTest() throws ItemInexistenteException {
		amigoService.delete(amigo.getId());
	}
	
	@Test (expected = ItemInexistenteException.class)
	public void deleteInvalidTest() throws ItemInexistenteException {
		amigoService.delete(123456);
	}
	
	@Test
	public void getAllTest() {
		assertTrue(amigoService.getAll().contains(amigo));
	}
	
	

}
