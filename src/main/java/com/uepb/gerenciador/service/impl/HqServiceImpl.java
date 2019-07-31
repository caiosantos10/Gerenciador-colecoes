package com.uepb.gerenciador.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uepb.gerenciador.exception.ItemInexistenteException;
import com.uepb.gerenciador.exception.ObjectNotFoundException;
import com.uepb.gerenciador.model.HQ;
import com.uepb.gerenciador.model.Usuario;
import com.uepb.gerenciador.model.enums.TipoFiltro;
import com.uepb.gerenciador.repository.HqRepository;
import com.uepb.gerenciador.service.IObjectService;

/**
 * Servico para tratar todos os Hq
 * 
 * @author Jose George e Caio
 *
 */
@Service
public class HqServiceImpl implements IObjectService<HQ> {
	private static final Logger logger = Logger.getLogger(HqServiceImpl.class);

	@Autowired
	private HqRepository itemRepository;

	@Autowired
	private UsuarioServiceImpl userService;
	
	/**
	 * <p>
	 * Este m�todo cria um registro de Hq
	 * </p>
	 * 
	 * @param dvd_cd Objeto de Hq que ser� persistido
	 * @return o registro de Hq que acaba de ser criado
	 */
	@Override
	public HQ create(HQ item) {
		logger.info("criando HQ");

		/**
		 * Recupera o user logado
		 */
		Usuario user = userService.getById(UserServiceImpl.authenticated().getId());

		/**
		 * Caso não tenha ninguém logado, retorna null
		 */
		if (user == null) {
			return null;
		}

		item.setUsuario(user);

		return itemRepository.save(item);
	}

	/**
	 * <p>
	 * Este m�todo recupera um registro de Hq pelo id correspondente
	 * </p>
	 * 
	 * @param id ID do Hq que deseja-se obter
	 * @return o registro de Hq com o id correspondente
	 */
	@Override
	public HQ getById(Integer id) {
		logger.info("Buscando HQ por id" + id);
		Optional<HQ> obj = itemRepository.findById(id);
		if (!obj.isPresent()) {
			logger.error("Hq nao encontrado, " + HqServiceImpl.class);
			throw new ObjectNotFoundException("Hq nao encontrado, id: " + id);
		}
		return obj.get();
	}

	/**
	 * <p>
	 * Este m�todo deleta um registro de Hq pelo id correspondente
	 * </p>
	 * 
	 * @param id ID do Hq que deseja-se deletar
	 * @throws Se objeto de Hq n�o existir
	 */
	@Override
	public void delete(Integer id) throws ItemInexistenteException {
		logger.info("deletando hq ");

		try {
			itemRepository.deleteById(id);
		} catch (Exception e) {
			logger.error("Este HQ nao existe, " + HqServiceImpl.class);
			throw new ItemInexistenteException("Impossível deletar o obj que não existe " + e.getMessage());
		}
	}

	/**
	 * <p>
	 * Este m�todo lista todos os registros de Hq
	 * </p>
	 * 
	 * @return uma lista com todos os registros de Hq
	 */
	@Override
	public List<HQ> getAll() {
		logger.info("listando todos os HQs");
		return itemRepository.findAll(UserServiceImpl.authenticated().getId());
	}

	/**
	 * <p>
	 * Este m�todo edita um registro de Hq
	 * </p>
	 * 
	 * @param id           ID do Hq que deseja-se editar
	 * @param amigoDetails objeto de Hq que leva os dados novos
	 * @return o registro de Hq editado
	 */
	@Override
	public HQ update(Integer HqId, HQ HqDetails) {
		HQ hq = this.getById(HqId);
		logger.info("atualizando hq " + hq.getTitulo());

		hq.setTitulo(HqDetails.getTitulo());
		hq.setPreco(HqDetails.getPreco());
		hq.setObservacoes(HqDetails.getObservacoes());
		hq.setSaga(HqDetails.getSaga());
		hq.setEditora(HqDetails.getEditora());
		hq.setUniverso(HqDetails.getUniverso());
		hq.setEstado(HqDetails.getEstado());
		hq.setStatusLeitura(HqDetails.isStatusLeitura());

		HQ updatedHq = itemRepository.save(hq);
		return updatedHq;

	}
	
	/**
	 * Busca HQ por filtro e tipo de filtro
	 * @param tipoFiltro tipo do filtro a ser aplicado
	 * @param filtro filtro 
	 * @return Lista de HQ de acordo com filtro
	 */
	public List<HQ> buscaPorFiltro(TipoFiltro tipoFiltro, String filtro){
		if(tipoFiltro.equals(TipoFiltro.TITULO)) {
			logger.info("Buscando por filtro de titulo :"+filtro);
			return itemRepository.filtraPorTitulo(UserServiceImpl.authenticated().getId(), filtro);
		}
		
		else if (tipoFiltro.equals(TipoFiltro.UNIVERSO)) {
			logger.info("Buscando por filtro de universo: "+filtro);
			return itemRepository.filtraPorUniverso(UserServiceImpl.authenticated().getId(), filtro);
		}
		
		else if (tipoFiltro.equals(TipoFiltro.EDITORA)) {
			logger.info("Buscando por filtro de editora: "+filtro);
			return itemRepository.filtraPorEditora(UserServiceImpl.authenticated().getId(), filtro);
		}
		
		return itemRepository.findAll(UserServiceImpl.authenticated().getId());
	}

}
