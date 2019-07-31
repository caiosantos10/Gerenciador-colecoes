package com.uepb.gerenciador.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uepb.gerenciador.exception.ObjectNotFoundException;
import com.uepb.gerenciador.exception.UsuarioInexistenteException;
import com.uepb.gerenciador.exception.UsuarioServicoException;
import com.uepb.gerenciador.model.Usuario;
import com.uepb.gerenciador.service.impl.UsuarioServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioTest {
	
	@Autowired
	private UsuarioServiceImpl service;
	
	private Usuario usuario;
	
	@Before
	public void setUp() throws UsuarioServicoException {
		usuario = new Usuario();
		usuario.setNome("Usuario");
		usuario.setEmail("usuario@hotmail.com");
		usuario.setSenha("12345");
		
		service.create(usuario);
	}
	
	@Test (expected = UsuarioServicoException.class)
	public void usuarioNuloTest() throws UsuarioServicoException {
		service.create(null);
	}
	
	@Test (expected = UsuarioServicoException.class)
	public void nomeNuloTest() throws UsuarioServicoException{
		Usuario user = new Usuario();
		user.setEmail("usuario@hotmail.com");
		user.setSenha("12345");
		service.create(user);
	}
	
	@Test (expected = UsuarioServicoException.class)
	public void senhaNulaTest() throws UsuarioServicoException {
		Usuario user = new Usuario();
		user.setNome("Usuario");
		user.setEmail("usuario@hotmail.com");
		service.create(user);
	}
	
	@Test (expected = UsuarioServicoException.class)
	public void emailNuloTest() throws UsuarioServicoException {
		Usuario user = new Usuario();
		user.setNome("Usuario");
		user.setSenha("12345");
		service.create(user);
	}
	
	
	@Test
	public void getByIdTest() {
		assertEquals(usuario, service.getById(usuario.getId()));
	}
	
	@Test (expected = ObjectNotFoundException.class)
	public void getByIvalidIdTest() {
		service.getById(123456);
	}
	
	@Test
	public void deleteTest() throws UsuarioInexistenteException {
		service.delete(usuario.getId());
	}
	
	@Test (expected =  UsuarioInexistenteException.class)
	public void deleteIvalidIdTest() throws UsuarioInexistenteException {
		service.delete(123456);
	}
	
	@Test
	public void getAll() {
		assertTrue(service.getAll().contains(usuario));
	}
	
	@Test 
	public void updateTest() {
		Usuario novo = new Usuario();
		novo.setNome("Caio");
		novo.setEmail("novoEmail@hotmail.com");
		novo.setSenha("123456");
		assertEquals(novo.getNome(), service.update(usuario.getId(), novo).getNome());
		assertEquals(novo.getEmail(), service.update(usuario.getId(), novo).getEmail());
		assertEquals(novo.getSenha(), service.update(usuario.getId(), novo).getSenha());
	}
	
	

}
