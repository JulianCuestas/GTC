package com.nuvu.test.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvu.test.persistence.model.Persona;
import com.nuvu.test.persistence.repo.IPersonaRepo;

@Service
public class PersonaService {
	
	@Autowired
	private IPersonaRepo personaRepo;
	
	public List<Persona> listarPersonas() {
		return personaRepo.findAll();
	}
	
	public int insertar(Persona per) {
		if(personaRepo.consultarPersonaByNumeroIdentificiacion(per.getNumeroIdentificacion()) == null) {
			Persona pers = personaRepo.save(per);
			return pers.getIdPersona();
		} else {
			return -1;//Código de registro duplicado
		}
	}
	
	public void consultar() {
	}
}
