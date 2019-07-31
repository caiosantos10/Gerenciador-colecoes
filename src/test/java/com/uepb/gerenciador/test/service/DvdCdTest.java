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
import com.uepb.gerenciador.model.DvdCd;
import com.uepb.gerenciador.model.enums.ItemEstado;
import com.uepb.gerenciador.service.impl.DvdCdServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DvdCdTest {

	@Autowired
	private DvdCdServiceImpl service;

	private DvdCd dvdCd;

	@Before
	public void setUp() {

		dvdCd = new DvdCd();
		
		dvdCd.setConteudo("Liga da justiça - o combate ainda maior");
		dvdCd.setEstado(ItemEstado.toEnum(1));
		dvdCd.setPreco(34.85);
		dvdCd.setStatusDeUso(false);
		dvdCd.setTitulo("Liga da justiça");
		dvdCd.setMarca("Multilaser");

		service.create(dvdCd);
	}
	
	@Test
	public void testUpdateDvdCd() throws ItemInexistenteException {

		dvdCd.setConteudo("liga_da_justica");
		service.update(dvdCd.getId(), dvdCd);
		DvdCd obj = service.getById(dvdCd.getId());
		assertEquals(obj.getConteudo(), "liga_da_justica");

	}

	@Test
	public void testListarDvdCd() {
		assertNotEquals(service.getAll(), null);
	}

	@Test
	public void testRecuparPorId() throws ItemInexistenteException {
		DvdCd obj = service.getById(dvdCd.getId());
		assertEquals(obj.getId(), dvdCd.getId());
	}
	
	@Test (expected = ObjectNotFoundException.class)
	public void testRecuparPorIdInexistente() {
		service.getById(1234567);
	}
	
	@Test(expected = ItemInexistenteException.class)
	public void deletarObjInexistente() throws ObjectNotFoundException, ItemInexistenteException {
		service.delete(123124);
	}
	
	@Test
	public void deletarObjTest() throws ItemInexistenteException {
		service.delete(dvdCd.getId());
	}	
	
}
