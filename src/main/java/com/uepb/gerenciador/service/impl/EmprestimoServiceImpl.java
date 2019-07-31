
package com.uepb.gerenciador.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.uepb.gerenciador.exception.EmprestimoServicoException;
import com.uepb.gerenciador.exception.ObjectNotFoundException;
import com.uepb.gerenciador.model.Emprestimo;
import com.uepb.gerenciador.model.Item;
import com.uepb.gerenciador.model.Usuario;
import com.uepb.gerenciador.repository.EmprestimoRepository;
import com.uepb.gerenciador.service.IEmprestimoService;

/**
 * Servico para tratar todos os Emprestimos
 * 
 * @author Jose George e Caio
 *
 */
@Service
public class EmprestimoServiceImpl implements IEmprestimoService {

	private static final Logger logger = Logger.getLogger(EmprestimoServiceImpl.class);

	@Autowired
	private EmprestimoRepository repository;

	@Autowired
	private ItemServiceImpl itemService;
	
	@Autowired
	private UsuarioServiceImpl userService;

	
	/**
	 * <p>
	 * Este metodo cria um Emprestimo
	 * </p>
	 * 
	 * @param emprestimo Objeto de Emprestimo
	 * @return o registro de Emprestimo criado
	 */
	@Override
	public Emprestimo create(Emprestimo emprestimo) throws EmprestimoServicoException{
		
		try {
			validar(emprestimo);
			logger.info("Criando Emprestimo");
			itemService.update(emprestimo.getItem().getId(), emprestimo.getItem());
			
			Usuario user = userService.getById(UserServiceImpl.authenticated().getId());
			
			
			if (user == null) {
				return null;
			}
		
			emprestimo.setUsuario(user);
			Item item = itemService.getById(emprestimo.getItem().getId());
			item.setEmprestado(true);
			emprestimo.setItem(item);
		
			return repository.save(emprestimo);
		}catch (EmprestimoServicoException e) {
			throw new EmprestimoServicoException("Erro ao criar emprestimo" + e.getMessage());
		}
	}

	/**
	 * <p>
	 * Este metodo recupera um registro de Emprestimo pelo id correspondente
	 * </p>
	 * 
	 * @param id ID do Emprestimo que deseja-se obter
	 * @return o registro de Emprestimo com o id correspondente
	 */
	@Override
	public Emprestimo getById(Integer id) throws ObjectNotFoundException {
		logger.info("Buscando Emprestimo ID " + id);
		Optional<Emprestimo> obj;
		try {
			obj = repository.findById(id);
		} catch (InvalidDataAccessApiUsageException e) {
			logger.error("Emprestimo nao encontrado, " + EmprestimoServiceImpl.class);
			throw new ObjectNotFoundException("Emprestimo nao encontrado, id: " + id);
		}

		return obj.get();
	}

	/**
	 * <p>
	 * Este m�todo edita um registro de Emprestimo
	 * </p>
	 * 
	 * @param id
	 * objeto de Emprestimo que leva os dados novos
	 * @param emprestimoDetails
	 * @return updatedEmprestimo
	 */
	@Override
	public Emprestimo update(Integer id, Emprestimo emprestimoDetails) throws ObjectNotFoundException {
		Emprestimo emprestimo = this.getById(id);
		logger.info("Atualizando Emprestimo ");
		try {
			emprestimo.setItem(emprestimoDetails.getItem());
			emprestimo.setAmigo(emprestimoDetails.getAmigo());
			emprestimo.setDataDeEmprestimo(emprestimoDetails.getDataDeEmprestimo());
			emprestimo.setDataDeDevolucao(emprestimoDetails.getDataDeDevolucao());
		} catch (NullPointerException e) {
			logger.error("O emprestimo que traz os dados novos esta nulo, " + EmprestimoServiceImpl.class);
			throw new ObjectNotFoundException("O emprestimo que traz os dados novos esta nulo");
		}

		Emprestimo updatedEmprestimo = repository.save(emprestimo);
		return updatedEmprestimo;
	}

	/**
	 * <p>
	 * Este metodo deleta um registro de Emprestimo pelo id correspondente
	 * </p>
	 * 
	 * @param id do Emprestimo que deseja-se deletar
	 * @throws ObjectNotFoundException
	 */
	@Override
	public void delete(Integer id) throws ObjectNotFoundException {
		logger.info("Deletando Emprestimo");

		try {
			
			devolucaoItem(id);
			repository.deleteById(id);
		} catch (InvalidDataAccessApiUsageException e) {
			logger.error("Objeto inexistente " + EmprestimoServiceImpl.class);
			throw new ObjectNotFoundException("Impossivel deletar o obj que nao existe " + e.getMessage());
		}
	}

	/**
	 * <p>
	 * Este metodo lista todos os registros de Emprestimo
	 * </p>
	 * 
	 * @return uma lista com todos os registros de Emprestimo
	 */
	@Override
	public List<Emprestimo> getAll() {
		logger.info("Listando todos os Emprestimo");
		return repository.findAll(UserServiceImpl.authenticated().getId());
	}
	
	private void validar(Emprestimo emprestimo) throws EmprestimoServicoException {
		
		if(emprestimo.getAmigo() == null) {
			throw new EmprestimoServicoException("O usuario não pode ser nulo");
		}
		
		if(itemService.verificarDisponibilidadeDeItem(emprestimo.getItem())) {
			throw new EmprestimoServicoException("O item já está emprestado");
		}
		
		/**
		 * fazer a comparação de datas aqui!
		 */
		
	}
	
	private void devolucaoItem(Integer id) {
		Emprestimo emprestimo = getById(id); 
		Item item = itemService.getById(emprestimo.getItem().getId());
		item.setEmprestado(false);
		itemService.update(item.getId(), item);
	}
	
}
