package com.uepb.gerenciador.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uepb.gerenciador.exception.ItemInexistenteException;
import com.uepb.gerenciador.exception.ObjectNotFoundException;
import com.uepb.gerenciador.model.Amigo;
import com.uepb.gerenciador.model.Usuario;
import com.uepb.gerenciador.repository.AmigoRepository;
import com.uepb.gerenciador.repository.EnderecoRepository;
import com.uepb.gerenciador.service.IObjectService;

/**
 * Servico para tratar todos os amigos
 * @author Jose George e Caio
 *
 */

@Service
public class AmigoServiceImpl implements IObjectService<Amigo> {
	private static final Logger logger = 
		      Logger.getLogger(AmigoServiceImpl.class);
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private AmigoRepository amigoRepository;
	
	@Autowired
	private UsuarioServiceImpl userService;
	
	/**
	 * <p> Este m�todo cria um registro de amigo </p>
	 * @param amigo Objeto de amigo que ser� persistido
	 * @return o registro de amigo que acaba de ser criado
	 */
	
	@Transactional
	@Override
	public Amigo create(Amigo amigo){
	
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
		
		logger.info("criando amigo, " + AmigoServiceImpl.class);
		amigo.setId(null);
		amigo = amigoRepository.save(amigo);
		enderecoRepository.saveAll(amigo.getEnderecos()); 
				
		amigo.setUsuario(user);
		
		return amigo;
		
	}
	
	/**
	 * <p> Este m�todo recupera um registro de amigo</p>
	 * @param id ID do amigo que deseja-se obter
	 * @return o registro de amigo com o id correspondente
	 */
	@Override
	public Amigo getById(Integer id) {
		logger.info("buscando amigo por id");
		Optional<Amigo> obj = amigoRepository.findById(id);
		
		if (!obj.isPresent()) {
			logger.error("Amigo nao encontrado, "+AmigoServiceImpl.class);
			throw new ObjectNotFoundException("Amigo nao encontrado, id: "+id);
		}
			
		return obj.get();
		
	}
	
	/**
	 * <p> Este m�todo edita um registro de amigo </p>
	 * @param id ID do amigo que deseja-se editar
	 * @param amigoDetails Objeto de Amigo que leva os dados novos
	 * @return o registro de amigo editado
	 */
	@Override
	public Amigo update(Integer amigoId, Amigo amigoDetails) {
		Amigo amigo = this.getById(amigoId);
		logger.info("atualizando registro do amigo "+amigo.getNome());
		
		if(amigoDetails.getNome() != null)
			amigo.setNome(amigoDetails.getNome());
		
		if(amigoDetails.getSexo() != null)
			amigo.setSexo(amigoDetails.getSexo());
		
		if(amigoDetails.getParentesco() != null)
			amigo.setParentesco(amigoDetails.getParentesco());
		
		if(amigoDetails.getEnderecos() != null)
			amigo.setEnderecos(amigoDetails.getEnderecos());
		
		if(amigoDetails.getTelefones() != null)
			amigo.setTelefones(amigoDetails.getTelefones());
		
		Amigo updatedAmigo = amigoRepository.save(amigo);
		return updatedAmigo;
	}

	/**
	 * <p> Este m�todo deleta um registro de amigo pelo id correspondente </p>
	 * @param id ID do amigo que deseja-se deletar
	 * @throws Se objeto nao existir
	 */
	@Override
	public void delete(Integer id) throws ItemInexistenteException {
		logger.info("deletando registro de amigo "+ AmigoServiceImpl.class);

		try {
			amigoRepository.deleteById(id);
		} catch (Exception e) {
			logger.error("Objeto inexistente "+ AmigoServiceImpl.class );
			throw new ItemInexistenteException("Objeto inexistente "+ AmigoServiceImpl.class);
			
		}
		
	}
	
	/**
	 * <p> Este m�todo lista todos os registros de Amigo</p>
	 * @return uma lista com todos os registros de amigo
	 */
	@Override
	public List<Amigo> getAll() {
		logger.info("listando todos os amigos");
		return amigoRepository.findAll(UserServiceImpl.authenticated().getId());
	}
	
}