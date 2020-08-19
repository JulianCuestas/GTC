package com.nuvu.test.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nuvu.test.persistence.model.TopeTarjetaCredito;

@Repository
public interface ITopeTarjetaCreditoRepo extends JpaRepository<TopeTarjetaCredito, Integer> {
	
}
