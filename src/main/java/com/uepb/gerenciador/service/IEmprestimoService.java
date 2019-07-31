package com.uepb.gerenciador.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uepb.gerenciador.exception.EmprestimoServicoException;
import com.uepb.gerenciador.exception.ObjectNotFoundException;
import com.uepb.gerenciador.model.Emprestimo;
/**
 * Interface para tratar operacoes sobre emprestimo
 * @author Caio e Jose George
 */
@Service
public interface IEmprestimoService {
	/**
	 * Cria um Emprestimo 
	 * @param emprestimo recebe o Emprestimo
	 * @return Emprestimo criado
	 */
	Emprestimo create(Emprestimo emprestimo) throws EmprestimoServicoException;
	
	/**
	 * Retorna o emprestimo procurado
	 * @param id identificador do emprestimo
	 * @return emprestimo encontrado
	 */
	Emprestimo getById(Integer id);
	
	/**
	 * responsavel por atualizar um Emprestimo
	 * 
	 * @param id id do Emprestimo a ser atualizado
	 * @param object Emprestimo com novos dados
	 * @return o Emprestimo atualizado
	 */
	Emprestimo update(Integer id, Emprestimo emprestimo);
	
	/**
	 * excluir um Emprestimo
	 * @param id id do Emprestimo
	 * @throws  caso o Emprestimo nao exista lanca a excecao: ItemInexistenteException
	 */
	void delete(Integer id) throws ObjectNotFoundException;
	

	/**
	 * 
	 * @return todos Emprestimos
	 */
	List <Emprestimo> getAll();

}
