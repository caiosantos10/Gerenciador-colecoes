package com.uepb.gerenciador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uepb.gerenciador.model.Usuario;

/**
 * Operacoes de CRUD sobre a Usuario
 * @author Caio e Jose George
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Usuario findByEmail(String email);
}
