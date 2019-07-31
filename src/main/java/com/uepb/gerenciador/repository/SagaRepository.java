package com.uepb.gerenciador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uepb.gerenciador.model.Saga;
/**
 * Operacoes de CRUD sobre a Saga dos HQs
 * @author Caio e Jose George
 */

public interface SagaRepository extends JpaRepository<Saga, Integer>{

}
