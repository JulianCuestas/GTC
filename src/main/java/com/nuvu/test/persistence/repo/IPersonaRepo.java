package com.nuvu.test.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nuvu.test.persistence.model.Persona;

@Repository
public interface IPersonaRepo extends JpaRepository<Persona, Integer> {
	
	@Query("SELECT per FROM Persona per WHERE numeroIdentificacion = ?1")
	Persona consultarPersonaByNumeroIdentificiacion(String numeroIdentificacion);
	
}
