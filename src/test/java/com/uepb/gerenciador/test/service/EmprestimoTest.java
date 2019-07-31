package com.uepb.gerenciador.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uepb.gerenciador.exception.EmprestimoServicoException;
import com.uepb.gerenciador.exception.ObjectNotFoundException;
import com.uepb.gerenciador.model.Amigo;
import com.uepb.gerenciador.model.Emprestimo;
import com.uepb.gerenciador.model.Item;
import com.uepb.gerenciador.model.enums.Sexo;
import com.uepb.gerenciador.service.impl.AmigoServiceImpl;
import com.uepb.gerenciador.service.impl.EmprestimoServiceImpl;
import com.uepb.gerenciador.service.impl.ItemServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmprestimoTest {

	@Autowired
	private EmprestimoServiceImpl emprestimoService;

	@Autowired
	private AmigoServiceImpl amigoService;

	@Autowired
	private ItemServiceImpl itemService;

	private Emprestimo emprestimo;
	private Emprestimo emprestimoNovo;
	private Item item;
	private Amigo amigo;

	@Before
	public void setUp() throws ParseException {
		amigo = new Amigo();
		item = new Item();
		
		emprestimo = new Emprestimo();
		item.setTitulo("Item de teste");
		item.setPreco(11.50);

		itemService.create(item);

		amigo.setNome("Joao Jose");
		amigo.setSexo(Sexo.MASCULINO);

		amigoService.create(amigo);

		emprestimo.setAmigo(amigo);
		emprestimo.setItem(item);
		emprestimo.setDataDeEmprestimo("18/10/2019");
		emprestimo.setDataDeDevolucao("22/10/2019");
	}

	/* Testando criacao de emprestimo correta */
	@Test(expected = EmprestimoServicoException.class)
	public void criacaoTest() throws ParseException, EmprestimoServicoException {
		assertEquals(emprestimoService.create(emprestimo), emprestimo);
	}

	/* Testando busca de emprestimo correta */
	@Test
	public void buscaTest() throws EmprestimoServicoException {
		emprestimoService.create(emprestimo);
		assertEquals(emprestimoService.getById(emprestimo.getId()), emprestimo);
	}

	/* Testando busca por emprestimo que nao est� persistido */
	@Test(expected = ObjectNotFoundException.class)
	public void buscaPorInexistenteTest() {
		emprestimoService.getById(emprestimo.getId());
	}

	/* Testando busca por valor nulo */
	@Test(expected = ObjectNotFoundException.class)
	public void buscaNulaTest() {
		emprestimoService.getById(null);
	}

	/* Testando update corretamente */
	@Test
	public void updateTest() throws ParseException, EmprestimoServicoException {
		emprestimoService.create(emprestimo);

		emprestimoNovo = new Emprestimo();
		emprestimoNovo.setAmigo(amigo);
		emprestimoNovo.setItem(item);
		emprestimoNovo.setDataDeEmprestimo("01/01/2000");
		emprestimoNovo.setDataDeDevolucao("10/01/2000");

		Emprestimo updatedEmprestimo = emprestimoService.update(emprestimo.getId(), emprestimoNovo);

		assertEquals(updatedEmprestimo.getAmigo(), emprestimoNovo.getAmigo());
		assertEquals(updatedEmprestimo.getItem(), emprestimoNovo.getItem());
		assertEquals(updatedEmprestimo.getDataDeDevolucao(), emprestimoNovo.getDataDeDevolucao());
		assertEquals(updatedEmprestimo.getDataDeEmprestimo(), emprestimoNovo.getDataDeEmprestimo());
	}

	/* Testando update passando emprestimo nulo */
	@Test(expected = ObjectNotFoundException.class)
	public void updateNuloTest() throws ParseException, EmprestimoServicoException {
		emprestimoService.create(emprestimo);
		emprestimoService.update(emprestimo.getId(), null);

	}

	// Testando delete ok
	@Test(expected = NoSuchElementException.class)
	public void deleteTest() throws EmprestimoServicoException {
		emprestimoService.create(emprestimo);
		emprestimoService.delete(emprestimo.getId());

		// busca emprestimo que ja foi deletado
		emprestimoService.getById(emprestimo.getId());
	}

	// Testando delete com emprestimo nulo
	@Test(expected = ObjectNotFoundException.class)
	public void deleteNuloTest() {
		emprestimoService.delete(null);
	}

	// Testando getAll de emprestimos 
	@Test
	public void getAllTest() throws EmprestimoServicoException {
		emprestimoService.create(emprestimo);
		assertTrue(emprestimoService.getAll().contains(emprestimo));;
	}

}
