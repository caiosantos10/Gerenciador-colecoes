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
import com.uepb.gerenciador.model.HQ;
import com.uepb.gerenciador.model.Saga;
import com.uepb.gerenciador.model.enums.ItemEstado;
import com.uepb.gerenciador.service.impl.HqServiceImpl;
import com.uepb.gerenciador.service.impl.SagaServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HqTest {

	@Autowired
	private HqServiceImpl service;
	
	@Autowired
	private SagaServiceImpl sagaService;

	private HQ hq;
	private Saga saga;

	@Before
	public void setUp() {

		hq = new HQ();
		saga = new Saga();
		
		saga.setNomeSaga("nomeSaga");
		saga.setNumeroSaga(2);
		sagaService.create(saga);

		hq.setEditora("Ecclesiae");
		hq.setUniverso("DC");
		hq.setEstado(ItemEstado.toEnum(1));
		hq.setTitulo("Imitação de Cristo");
		hq.setPreco(44.00);
		hq.setSaga(saga);
		hq.setStatusLeitura(true);
		

		service.create(hq);

	}

	@Test
	public void testListarHq() {
		assertNotEquals(service.getAll(), null);
	}
	
	@Test
	public void testUpdateHq() throws ItemInexistenteException {

		hq.setEditora("Paullus");
		service.update(hq.getId(), hq);
		HQ obj = service.getById(hq.getId());
		assertEquals(obj.getEditora(), "Paullus");
	}

	@Test
	public void testRecuparPorId() throws ItemInexistenteException {
		HQ obj = service.getById(hq.getId()); 
		assertEquals(obj.getId(), hq.getId());
	}
	
	@Test (expected = ObjectNotFoundException.class)
	public void testRecuparPorIdInexistente() {
		service.getById(12345); 

	}
	
	@Test(expected = ItemInexistenteException.class)
	public void deletarObjInexistente() throws ObjectNotFoundException, ItemInexistenteException {
		service.delete(12212313);
	}
	
	@Test
	public void deletarObj() throws ObjectNotFoundException, ItemInexistenteException {
		service.delete(hq.getId());
	}
	
	

}
