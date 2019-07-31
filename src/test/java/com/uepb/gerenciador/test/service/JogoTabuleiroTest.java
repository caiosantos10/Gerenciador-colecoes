package com.uepb.gerenciador.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uepb.gerenciador.exception.ItemInexistenteException;
import com.uepb.gerenciador.exception.ObjectNotFoundException;
import com.uepb.gerenciador.model.JogoDeTabuleiro;
import com.uepb.gerenciador.model.enums.ItemEstado;
import com.uepb.gerenciador.service.impl.JogoDeTabuleiroServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JogoTabuleiroTest {

	
	@Autowired
	private JogoDeTabuleiroServiceImpl service;
	
	private JogoDeTabuleiro jogoTab; 
	
	@Before
	public void setUp() {
		
		jogoTab = new JogoDeTabuleiro();
		
		jogoTab.setObservacoes("dificil");
		jogoTab.setPreco(25.00);
		jogoTab.setTitulo("Xadrez");
		jogoTab.setEstado(ItemEstado.NOVO);
		
		service.create(jogoTab);
		
	}
	
	@Test
	public void testUpdateJogoTab() throws ItemInexistenteException {
		
		jogoTab.setObservacoes("competitivo");	
		service.update(jogoTab.getId(), jogoTab);
		JogoDeTabuleiro obj = service.getById(jogoTab.getId());
		assertEquals(obj.getObservacoes(),"competitivo");
	}
	
	@Test
	public void testListaJogoTab() {
		assertNotEquals(service.getAll(), null);
	}
	
	@Test
	public void testRecuparPorId() throws ItemInexistenteException {
		JogoDeTabuleiro obj = service.getById(jogoTab.getId());
		assertEquals(obj.getId(), jogoTab.getId());
	}
	
	@Test (expected = ObjectNotFoundException.class)
	public void testRecuparPorIdNulo()  {
		service.getById(12345);
		
	}
	
	@Test(expected = ItemInexistenteException.class)
	public void deletarObjInexistente() throws ObjectNotFoundException, ItemInexistenteException {
		service.delete(1223434);
	}
	
	@Test
	public void deletarObj() throws ObjectNotFoundException, ItemInexistenteException {
		service.delete(jogoTab.getId());
	}
	
}
