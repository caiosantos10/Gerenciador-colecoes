package com.uepb.gerenciador.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uepb.gerenciador.exception.ItemInexistenteException;
import com.uepb.gerenciador.exception.ObjectNotFoundException;
import com.uepb.gerenciador.model.Item;
import com.uepb.gerenciador.model.Usuario;
import com.uepb.gerenciador.model.enums.TipoFiltro;
import com.uepb.gerenciador.repository.ItemRepository;
import com.uepb.gerenciador.service.IObjectService;

/**
 * Servico para tratar todos os itens
 * 
 * @author Jose George e Caio
 *
 */
@Service
public class ItemServiceImpl implements IObjectService<Item> {
	private static final Logger logger = Logger.getLogger(ItemServiceImpl.class);

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private UsuarioServiceImpl userService;

	private List<Item> itensRepetidos = new ArrayList<>();

	/**
	 * <p>
	 * Este m�todo cria um registro de Item
	 * </p>
	 * 
	 * @param dvd_cd Objeto de Item que ser� persistido
	 * @return o registro de Item que acaba de ser criado
	 */
	@Override
	public Item create(Item item) {
		logger.info("Criando item");

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
	 * Este metodo recupera um registro de Item pelo id correspondente
	 * </p>
	 * 
	 * @param id ID do Item que deseja-se obter
	 * @return o registro de Item com o id correspondente
	 */
	@Override
	public Item getById(Integer id) {
		logger.info("Recuperando item por id " + id);
		Optional<Item> obj = itemRepository.findById(id);

		if (!obj.isPresent()) {
			logger.error("Item nao encontrado, " + ItemServiceImpl.class);
			throw new ObjectNotFoundException("Item nao encontrado, id: " + id);
		}
		return obj.get();
	}

	/**
	 * <p>
	 * Este m�todo edita um registro de Item
	 * </p>
	 * 
	 * @param id           ID do Item que deseja-se editar
	 * @param amigoDetails objeto de Item que leva os dados novos
	 * @return o registro de Item editado
	 */
	@Override
	public Item update(Integer itemId, Item itemDetails) {
		Item item = this.getById(itemId);
		logger.info("atualizando item " + item.getTitulo());

		item.setPreco(itemDetails.getPreco());
		item.setObservacoes(itemDetails.getObservacoes());
		item.setTitulo(itemDetails.getTitulo());
		item.setEmprestado(itemDetails.isEmprestado());
		
		Item updatedItem = itemRepository.save(item);
		return updatedItem;
	}

	/**
	 * <p>
	 * Este m�todo deleta um registro de DvdCd pelo id correspondente
	 * </p>
	 * 
	 * @param id ID do DvdCd que deseja-se deletar
	 * @throws Se objeto de DvdCd n�o existir
	 */
	@Override
	public void delete(Integer id) throws ItemInexistenteException {
		logger.info("deletando item");

		try {
			itemRepository.deleteById(id);
		} catch (Exception e) {
			logger.error("item inexistente, " + ItemServiceImpl.class);
			throw new ItemInexistenteException("Impossível deletar o obj que não existe " + e.getMessage());
		}

	}

	/**
	 * <p>
	 * Este m�todo lista todos os registros de Item da base de dados
	 * </p>
	 * 
	 * @return uma lista com todos os registros de Item
	 */
	@Override
	public List<Item> getAll() {
		logger.info("recuperando todos os itens");
		return itemRepository.findAll(UserServiceImpl.authenticated().getId());
	}

	/**
	 * Busca itens filtrando pelo titulo
	 * @param titulo filtro da busca
	 * @return lista de itens com o titulo
	 */
	public List<Item> findByTitulo(String titulo) {
		logger.info("Busca de itens pelo titulo " + titulo);
		return itemRepository.findByTitulo(titulo);
	}

	/**
	 * Retorna itens repetidos
	 * @return retorna uma lista de itens repetidos
	 */
	public List<Item> getItensRepetidos() {
		logger.info("Executando getItensRepetidos");
		List<Item> aux = this.getAll();
		for (Item item : aux) {
			for (Item outroItem : aux) {
				// verificando se existem itens repetidos e eliminando as comparacoes do item
				// com ele mesmo
				if (item.isEqual(outroItem) && !item.equals(outroItem)) {
					logger.info("item: " + item + " " + " outro item: " + outroItem);
					if (!itensRepetidos.contains(outroItem) && !itensRepetidos.contains(item)) {
						itensRepetidos.add(outroItem);
						itensRepetidos.add(item);
					}
				}
			}
		}
		return itensRepetidos;
	}
	
	/**
	 * Verifica Disponiilidade do Item
	 * @param item a ser verificado
	 * @return true se item disponivel, false se nao
	 */
	public boolean verificarDisponibilidadeDeItem(Item item) {
		if(item.isEmprestado()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Retorna Lista de itens ordenados por numero de emprestimos
	 * @return List<Item> ranking
	 */
	public List<Item> getRanking(){
		logger.info("Gerando ranking");
		return itemRepository.ranking(UserServiceImpl.authenticated().getId());
	}
	
	/**
	 * Busca itens por filtro e tipo de filtro
	 * @param tipoFiltro tipo do filtro a ser aplicado
	 * @param filtro filtro 
	 * @return Lista de itens de acordo com filtro
	 */
	public List<Item> buscaPorFiltro(TipoFiltro tipoFiltro, String filtro){
		if(tipoFiltro.equals(TipoFiltro.TITULO)) {
			logger.info("Buscando por filtro de titulo :"+filtro);
			return itemRepository.filtraPorTitulo(UserServiceImpl.authenticated().getId(), filtro);
		}
		
		return itemRepository.findAll(UserServiceImpl.authenticated().getId());
	}
	
	
	
}
