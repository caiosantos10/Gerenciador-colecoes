package com.uepb.gerenciador.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uepb.gerenciador.exception.ItemInexistenteException;
import com.uepb.gerenciador.exception.ObjectNotFoundException;
import com.uepb.gerenciador.model.Item;
import com.uepb.gerenciador.service.impl.ItemServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemTest {

	@Autowired
	private ItemServiceImpl service;

	private Item item;

	@Before
	public void setUp() {

		item = new Item();

		item.setObservacoes("teste");
		item.setPreco(77.90);
		item.setTitulo("teste_teste");

		service.create(item);
	}

	@Test
	public void testRecuparPorId() throws ItemInexistenteException {
		Item obj = service.getById(item.getId());
		assertEquals(obj.getId(), item.getId());
	}

	@Test(expected = ObjectNotFoundException.class)
	public void testRecuparPorIdInexistente() throws ItemInexistenteException {
		service.getById(123456);

	}

	@Test
	public void testUpdateItem() throws ItemInexistenteException {

		item.setTitulo("titulo");
		service.update(item.getId(), item);

		Item obj = service.getById(item.getId());

		assertEquals("titulo", obj.getTitulo());

	}

	@Test
	public void texstListarItem() {
		assertNotEquals(service.getAll(), null);
	}

	@Test(expected = ItemInexistenteException.class)
	public void deletarObjInexistente() throws ObjectNotFoundException, ItemInexistenteException {
		service.delete(122123123);
	}

	@Test
	public void deletarObj() throws ObjectNotFoundException, ItemInexistenteException {
		service.delete(item.getId());
	}

	@Test
	public void buscaPorTitulo() {
		assertTrue(service.findByTitulo("teste_teste").contains(item));
	}

	@Test
	public void itensRepetidosTest() {
		Item itemNovo = new Item();

		itemNovo.setObservacoes("adicionando item para teste");
		itemNovo.setPreco(77.90);
		itemNovo.setTitulo("teste_teste");
		
		service.create(itemNovo);
		
		System.out.println(service.getItensRepetidos().toString());
	}

}
