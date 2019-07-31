package com.uepb.gerenciador.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uepb.gerenciador.exception.ItemInexistenteException;
import com.uepb.gerenciador.exception.ObjectNotFoundException;
import com.uepb.gerenciador.model.JogoDeVideogame;
import com.uepb.gerenciador.model.Usuario;
import com.uepb.gerenciador.model.enums.TipoFiltro;
import com.uepb.gerenciador.repository.JogoDeVideogameRepository;
import com.uepb.gerenciador.service.IObjectService;

/**
 * Servico para tratar todos os jogos de video game
 * @author Jose George e Caio
 *
 */

@Service
public class JogoDeVideogameServiceImpl implements IObjectService <JogoDeVideogame> {
	private static final Logger logger = 
		      Logger.getLogger(JogoDeVideogameServiceImpl.class);

	@Autowired
	private JogoDeVideogameRepository itemRepo;
	
	@Autowired
	private UsuarioServiceImpl userService;

	/**
	 * <p> Este m�todo cria um registro de JogoDeVideoGame </p>
	 * @param dvd_cd Objeto de JogoDeVideoGame que ser� persistido
	 * @return o registro de JogoDeVideoGame que acaba de ser criado
	 */
	@Override
	public JogoDeVideogame create(JogoDeVideogame item) {
		logger.info("Criando jogo");
		
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
	 * <p> Este m�todo recupera um registro de JogoDeVideoGame pelo id correspondente </p>
	 * @param id ID do JogoDeVideoGame que deseja-se obter
	 * @return o registro de JogoDeVideoGame com o id correspondente
	 */
	@Override
	public JogoDeVideogame getById(Integer id) {
		logger.info("Recuperando jogo por id");
		Optional<JogoDeVideogame> obj = itemRepo.findById(id);
		
		if (!obj.isPresent()) {
			logger.error("JogoDeVideoGame nao encontrado, "+JogoDeVideogameServiceImpl.class);
			throw new ObjectNotFoundException("Jogo nao encontrado, id: "+id);
		}
		return obj.get();
	}	
	
	/**
	 * <p> Este m�todo deleta um registro de JogoDeVideoGame pelo id correspondente </p>
	 * @param id ID do JogoDeVideoGame que deseja-se deletar
	 * @throws Se objeto de JogoDeVideoGame n�o existir
	 */
	@Override
	public void delete(Integer id) throws ItemInexistenteException {
		
		logger.info("deletando jogo");

		try {
			itemRepo.deleteById(id);
		} catch (Exception e) {
			logger.error("jogo inexistente, "+JogoDeVideogameServiceImpl.class);
			throw new ItemInexistenteException("Impossível deletar o obj que não existe " + e.getMessage());
		}

	}
	
	/**
	 * <p> Este m�todo lista todos os registros de JogoDeVideogame </p>
	 * @return uma lista com todos os registros de JogoDeVideogame
	 */
	@Override
	public List<JogoDeVideogame> getAll() {
		logger.info("Listando todos os jogos");
		return itemRepo.findAll(UserServiceImpl.authenticated().getId());
	}
	
	/**
	 * <p> Este m�todo edita um registro de DvdCd </p>
	 * @param id ID do DvdCd que deseja-se editar
	 * @param amigoDetails objeto de DvdCd que leva os dados novos
	 * @return o registro de DvdCd editado
	 */
	@Override
	public JogoDeVideogame update(Integer jogoId, JogoDeVideogame jogoDetails) {
		JogoDeVideogame jogo = (JogoDeVideogame) this.getById(jogoId);
		logger.info("Editando jogo "+jogo.getTitulo());
		

		jogo.setTitulo(jogoDetails.getTitulo());
		jogo.setPreco(jogoDetails.getPreco());
		jogo.setObservacoes(jogoDetails.getObservacoes());
		jogo.setEstado(jogoDetails.getEstado());	
		
		JogoDeVideogame updatedJogo = itemRepo.save(jogo);
	    return updatedJogo;

	}
	
	/**
	 * Busca JogoDeVideogame por filtro e tipo de filtro
	 * @param tipoFiltro tipo do filtro a ser aplicado
	 * @param filtro filtro 
	 * @return Lista de JogoDeVideogame de acordo com filtro
	 */
	public List<JogoDeVideogame> buscaPorFiltro(TipoFiltro tipoFiltro, String filtro){
		if(tipoFiltro.equals(TipoFiltro.TITULO)) {
			logger.info("Buscando por filtro de titulo :"+filtro);
			return itemRepo.filtraPorTitulo(UserServiceImpl.authenticated().getId(), filtro);
		}
		
		else if (tipoFiltro.equals(TipoFiltro.CONSOLE)) {
			logger.info("Buscando por filtro de console: "+filtro);
			return itemRepo.filtraPorConsole(UserServiceImpl.authenticated().getId(), filtro);
		}
		
		
		return itemRepo.findAll(UserServiceImpl.authenticated().getId());
	}

}
