package com.uepb.gerenciador.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uepb.gerenciador.exception.ItemInexistenteException;
import com.uepb.gerenciador.exception.ObjectNotFoundException;
import com.uepb.gerenciador.model.JogoDeTabuleiro;
import com.uepb.gerenciador.model.Usuario;
import com.uepb.gerenciador.model.enums.TipoFiltro;
import com.uepb.gerenciador.repository.JogoDeTabuleiroRepository;
import com.uepb.gerenciador.service.IObjectService;

/**
 * Servico para tratar todos os jogos de tabuleiro
 * @author Jose George e Caio
 *
 */
@Service
public class JogoDeTabuleiroServiceImpl implements IObjectService <JogoDeTabuleiro> {
	private static final Logger logger = 
		      Logger.getLogger(JogoDeTabuleiroServiceImpl.class);

	@Autowired
	private JogoDeTabuleiroRepository itemRepo;
	
	@Autowired
	private UsuarioServiceImpl userService;

	/**
	 * <p> Este m�todo cria um registro de JogoDeTabuleiro </p>
	 * @param dvd_cd Objeto de JogoDeTabuleiro que ser� persistido
	 * @return o registro de JogoDeTabuleiro que acaba de ser criado
	 */
	@Override
	public JogoDeTabuleiro create(JogoDeTabuleiro item) {
		logger.info("Criando Jogo de Tabuleiro");
		
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
		
		return itemRepo.save(item);
	}
	
	/**
	 * <p> Este m�todo recupera um registro de JogoDeTabuleiro pelo id correspondente </p>
	 * @param id ID do JogoDeTabuleiro que deseja-se obter
	 * @return o registro de JogoDeTabuleiro com o id correspondente
	 */
	@Override
	public JogoDeTabuleiro getById(Integer id) {
		logger.info("recuperando Jogo de Tabuleiro por id "+id);
		Optional<JogoDeTabuleiro> obj = itemRepo.findById(id);
		
		if (!obj.isPresent()) {
			logger.error("JogoDeTabuleiro nao encontrado, "+JogoDeTabuleiroServiceImpl.class);
			throw new ObjectNotFoundException("JogoDeTabuleiro nao encontrado, id: "+id);
		}
		return obj.get();

	}
	
	/**
	 * <p> Este m�todo deleta um registro de JogoDeTabuleiro pelo id correspondente </p>
	 * @param id ID do JogoDeTabuleiro que deseja-se deletar
	 * @throws Se objeto de JogoDeTabuleiro n�o existir
	 */
	@Override
	public void delete(Integer id) throws ItemInexistenteException {
		logger.info("Deletando jogo de tabuleiro");

		try {
			itemRepo.deleteById(id);
		} catch (Exception e) {
			logger.error("este Jogo de tabuleiro nao existe, "+JogoDeTabuleiroServiceImpl.class);
			throw new ItemInexistenteException("Impossível deletar o obj que não existe " + e.getMessage());
		}
		
	}
	
	/**
	 * <p> Este m�todo lista todos os registros de JogoDeTabuleiro </p>
	 * @return uma lista com todos os registros de JogoDeTabuleiro
	 */
	@Override
	public List<JogoDeTabuleiro> getAll() {
		logger.info("Recuperando todos os registros de jogo de tabuleiro");
		return itemRepo.findAll(UserServiceImpl.authenticated().getId());
	}
	
	/**
	 * <p> Este m�todo edita um registro de JogoDeTabuleiro </p>
	 * @param id ID do JogoDeTabuleiro que deseja-se editar
	 * @param amigoDetails objeto de JogoDeTabuleiro que leva os dados novos
	 * @return o registro de JogoDeTabuleiro editado
	 */
	@Override
	public JogoDeTabuleiro update(Integer jogoId, JogoDeTabuleiro jogoDetails) {

		JogoDeTabuleiro jogo = this.getById(jogoId);
		logger.info("atualizando jogo de tabuleiro" + jogo.getTitulo());

		jogo.setTitulo(jogoDetails.getTitulo());
		jogo.setPreco(jogoDetails.getPreco());
		jogo.setObservacoes(jogoDetails.getObservacoes());
		jogo.setEstado(jogoDetails.getEstado());		
		
		JogoDeTabuleiro updatedJogo = itemRepo.save(jogo);
	    return updatedJogo;

	}
	
	/**
	 * Busca JogoDeTabuleiro por filtro e tipo de filtro
	 * @param tipoFiltro tipo do filtro a ser aplicado
	 * @param filtro filtro 
	 * @return Lista de JogoDeTabuleiro de acordo com filtro
	 */
	public List<JogoDeTabuleiro> buscaPorFiltro(TipoFiltro tipoFiltro, String filtro){
		if(tipoFiltro.equals(TipoFiltro.TITULO)) {
			logger.info("Buscando por filtro de titulo :"+filtro);
			return itemRepo.filtraPorTitulo(UserServiceImpl.authenticated().getId(), filtro);
		}
		
		return itemRepo.findAll(UserServiceImpl.authenticated().getId());
	}

}
