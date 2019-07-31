package com.uepb.gerenciador.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uepb.gerenciador.exception.ItemInexistenteException;
import com.uepb.gerenciador.exception.ObjectNotFoundException;
import com.uepb.gerenciador.model.DvdCd;
import com.uepb.gerenciador.model.Usuario;
import com.uepb.gerenciador.model.enums.TipoFiltro;
import com.uepb.gerenciador.repository.DvdCdRepository;
import com.uepb.gerenciador.service.IObjectService;

/**
 * Servico para tratar todos os itens Dvd_cd
 * @author Jose George e Caio
 *
 */

@Service
public class DvdCdServiceImpl implements IObjectService<DvdCd> {
	
	private static final Logger logger = 
		      Logger.getLogger(DvdCdServiceImpl.class);

	@Autowired
	private DvdCdRepository itemRepository;	
	
	@Autowired
	private UsuarioServiceImpl userService;
	
	/**
	 * <p> Este metodo cria um registro de DvdCD </p>
	 * @param dvd_cd Objeto de DvdCd que ser� persistido
	 * @return o registro de DvdCd que acaba de ser criado
	 */
	@Override
	public DvdCd create(DvdCd dvd_cd) {
		logger.info("Criando registro de DVD/CD: "+dvd_cd.getTitulo());
			
		/**
		 * Recupera o user logado
		 */
		Usuario user = userService.getById(UserServiceImpl.authenticated().getId());
		
		/**
		 * Caso não tenha ninguém logado, retorna null
		 */
		if(user == null) {
			return null;
		}
			
		dvd_cd.setUsuario(user);
		return itemRepository.save(dvd_cd);
	}
	
	/**
	 * <p> Este metodo recupera um registro de DvdCd pelo id correspondente </p>
	 * @param id ID do DvdCd que deseja-se obter
	 * @return o registro de DvdCd com o id correspondente
	 */
	@Override
	public DvdCd getById(Integer id) {
		logger.info("Buscando DVD/CD por ID "+id);
		Optional<DvdCd> obj = itemRepository.findById(id);
		if (!obj.isPresent()) {
			logger.error("DvdCd nao encontrado, "+DvdCdServiceImpl.class);
			throw new ObjectNotFoundException("DvdCd nao encontrado, id: "+id);
		}
		
		return obj.get();
	}
	
	/**
	 * <p> Este m�todo deleta um registro de DvdCd pelo id correspondente </p>
	 * @param id ID do DvdCd que deseja-se deletar
	 * @throws Se objeto de DvdCd n�o existir
	 */
	@Override
	public void delete(Integer id) throws ItemInexistenteException {
		logger.info("Deletando DVD/CD ");
		
		try {
			itemRepository.deleteById(id);
		} catch (Exception e) {
			logger.error("Objeto inexistente "+ DvdCdServiceImpl.class );
			throw new ItemInexistenteException("Impossível deletar o obj que não existe " + e.getMessage());
		}
	}
	
	/**
	 * <p> Este metodo lista todos os registros de DvdCd </p>
	 * @return uma lista com todos os registros de DvdCd
	 */
	@Override
	public List<DvdCd> getAll() {
		logger.info("Listando todos os DVDs/CDs");
		return itemRepository.findAll(UserServiceImpl.authenticated().getId());
	}
	
	/**
	 * <p> Este metodo edita um registro de DvdCd </p>
	 * @param id ID do DvdCd que deseja-se editar
	 * @param amigoDetails objeto de DvdCd que leva os dados novos
	 * @return o registro de DvdCd editado
	 */
	@Override
	public DvdCd update(Integer dvdCdId, DvdCd dvdCdDetails) {
		DvdCd dvd_cd = (DvdCd) this.getById(dvdCdId);
		logger.info("Atualizando DVD/CD "+dvd_cd.getTitulo());

		dvd_cd.setTitulo(dvdCdDetails.getTitulo());
		dvd_cd.setPreco(dvdCdDetails.getPreco());
		dvd_cd.setObservacoes(dvdCdDetails.getObservacoes());
		dvd_cd.setConteudo(dvdCdDetails.getConteudo());
		dvd_cd.setEstado(dvdCdDetails.getEstado());
		dvd_cd.setMarca(dvdCdDetails.getMarca());
		dvd_cd.setStatusDeUso(dvdCdDetails.isStatusDeUso());

		DvdCd updatedDvdCd = itemRepository.save(dvd_cd);
		return updatedDvdCd;

	}
	
	/**
	 * Busca DvdCd por filtro e tipo de filtro
	 * @param tipoFiltro tipo do filtro a ser aplicado
	 * @param filtro filtro 
	 * @return Lista de DvdCd de acordo com filtro
	 */
	public List<DvdCd> buscaPorFiltro(TipoFiltro tipoFiltro, String filtro){
		if(tipoFiltro.equals(TipoFiltro.TITULO)) {
			logger.info("Buscando por filtro de titulo :"+filtro);
			return itemRepository.filtraPorTitulo(UserServiceImpl.authenticated().getId(), filtro);
		}
		
		else if (tipoFiltro.equals(TipoFiltro.MARCA)) {
			logger.info("Buscando por filtro de marca: "+filtro);
			return itemRepository.filtraPorMarca(UserServiceImpl.authenticated().getId(), filtro);
		}
		
		return itemRepository.findAll(UserServiceImpl.authenticated().getId());
	}

}
