package com.uepb.gerenciador.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import com.uepb.gerenciador.exception.ItemInexistenteException;
import com.uepb.gerenciador.exception.ObjectNotFoundException;
import com.uepb.gerenciador.model.JogoDeVideogame;
import com.uepb.gerenciador.model.enums.ItemEstado;
import com.uepb.gerenciador.service.impl.JogoDeVideogameServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JogoVideogameTest {

	@Autowired
	private JogoDeVideogameServiceImpl service;

	private JogoDeVideogame jogVid;

	@Before
	public void setUp() {

		jogVid = new JogoDeVideogame();
		
		jogVid.setConsole("3x");
		jogVid.setPreco(44.5);
		jogVid.setTitulo("Mario");
		jogVid.setEstado(ItemEstado.RASURADO);
		service.create(jogVid);
	}
	
	@Test
	public void testUpdateJogoTab() throws ItemInexistenteException {
		jogVid.setTitulo("Mortal Kombat");
		service.update(jogVid.getId(), jogVid);
		JogoDeVideogame obj = service.getById(jogVid.getId());
		assertEquals(obj.getTitulo(),"Mortal Kombat");
	}
	
	@Test
	public void testListarHq() {
		assertNotEquals(service.getAll(), null);
	}
	 
	@Test
	public void testRecuparPorId() throws ItemInexistenteException {
		JogoDeVideogame obj = service.getById(jogVid.getId());
		assertEquals(obj.getId(), jogVid.getId());
	} 
	
	@Test (expected = ObjectNotFoundException.class)
	public void recuparPorIdInexistente() throws ObjectNotFoundException{
		service.getById(12345);
		
	}
	
	@Test (expected = InvalidDataAccessApiUsageException.class)
	public void recuparPorIdNulo() throws ObjectNotFoundException{
		service.getById(null);
		
	}
	
	@Test(expected = ItemInexistenteException.class)
	public void deletarObjInexistente() throws ObjectNotFoundException, ItemInexistenteException {
		service.delete(null);
	}
	
	@Test
	public void deletarObj() throws ItemInexistenteException {
		service.delete(jogVid.getId());
	}	

}
