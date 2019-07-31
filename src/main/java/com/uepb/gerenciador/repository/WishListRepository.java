package com.uepb.gerenciador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uepb.gerenciador.model.WishList;

public interface WishListRepository extends JpaRepository<WishList, Integer> {


	@Query("SELECT obj FROM WishList obj WHERE obj.usuario.id = :id")
	public List<WishList> findAll(@Param("id") Integer id);
	
}
