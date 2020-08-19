package com.nuvu.test.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nuvu.test.persistence.model.TarjetaCredito;

@Repository
public interface ITarjetaCreditoRepo extends JpaRepository<TarjetaCredito, Integer> {
	
	@Query("SELECT tc FROM TarjetaCredito tc WHERE numeroTarjeta = ?1")
	TarjetaCredito consultarTarjetaByNumeroTarjeta(String numeroTarjeta);
	
	@Query("SELECT tc FROM TarjetaCredito tc WHERE idPersona.idPersona = ?1")
	List<TarjetaCredito> listarTarjetasPersona(Integer idPersona);
}
