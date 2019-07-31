package com.uepb.gerenciador.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uepb.gerenciador.exception.ItemInexistenteException;
import com.uepb.gerenciador.exception.ObjectNotFoundException;
import com.uepb.gerenciador.model.Saga;
import com.uepb.gerenciador.repository.SagaRepository;
import com.uepb.gerenciador.service.IObjectService;

@Service
public class SagaServiceImpl implements IObjectService<Saga> {
	
	private static final Logger logger = Logger.getLogger(SagaServiceImpl.class);

	@Autowired
	private SagaRepository repository;

	/**
	 * Cria uma saga
	 * 
	 * @param objeto saga a ser persistido
	 * @return retorna a saga criada
	 */
	@Override
	public Saga create(Saga object) {
		logger.info("Criando Saga");
		return repository.save(object);
	}

	/**
	 * Recupera saga por id
	 * 
	 * @param id identificador da saga
	 * @return Saga com id correspondente
	 */
	@Override
	public Saga getById(Integer id) {
		logger.info("Recuperando Saga por id " + id);
		Optional<Saga> obj = repository.findById(id);
		if (!obj.isPresent()) {
			logger.error("Saga nao encontrado, " + SagaServiceImpl.class);
			throw new ObjectNotFoundException("Saga nao encontrado, id: " + id);
		}
		return obj.get();
	}

	/**
	 * Faz update de informacoes de Saga
	 * 
	 * @param id     id para recuperar Saga que vai ser modificada
	 * @param objeto com dados novos a ser persistidos
	 * @return Saga atualizada
	 */
	@Override
	public Saga update(Integer id, Saga object) {
		logger.info("Update de saga");
		Saga saga = this.getById(id);

		saga.setNomeSaga(object.getNomeSaga());
		saga.setNumeroSaga(object.getNumeroSaga());

		Saga updatedSaga = repository.save(saga);
		return updatedSaga ;
	}
	
	/**
	 * Deleta Saga
	 * @param id id da Saga
	 */
	@Override
	public void delete(Integer id) throws ItemInexistenteException {
		logger.info("deletando saga ");

		try {
			repository.deleteById(id);
		} catch (Exception e) {
			logger.error("Esta Saga nao existe, "+SagaServiceImpl.class);
			throw new ItemInexistenteException("Impossível deletar o obj que não existe " + e.getMessage());
		}

	}
	/**
	 * Lista todas as Sagas
	 * @return Lista de sagas
	 */
	@Override
	public List<Saga> getAll() {
		logger.info("listando todos as Sagas");
		return repository.findAll();
	}

}
