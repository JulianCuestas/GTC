package com.nuvu.test.business.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuvu.test.application.global.EMensajeEstandar;
import com.nuvu.test.application.global.RespuestaDTO;
import com.nuvu.test.business.service.PersonaService;
import com.nuvu.test.persistence.model.Persona;

@RestController
@RequestMapping("/personas")
public class PersonaRest {
	
	@Autowired
	private PersonaService personaService;
	
	@GetMapping
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RespuestaDTO listar() {
		List<Persona> lista = personaService.listarPersonas();
		if(!lista.isEmpty()) {
			return new RespuestaDTO().setCodigo(EMensajeEstandar.OK.getCodigo()).setMensaje(EMensajeEstandar.OK.getMensaje()).setDatos(lista).setStatus(EMensajeEstandar.OK.isStatus());
		} else {
			return new RespuestaDTO(EMensajeEstandar.NO_RESULTADOS);
		}
	}
	
	@PostMapping("/new")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RespuestaDTO insertar(@RequestBody Persona per) {
		int idPerson = personaService.insertar(per);
		if(idPerson >= 1) {
			return new RespuestaDTO().setDatos(idPerson);
		} else {
			return new RespuestaDTO(EMensajeEstandar.DUPLICADO);
		}
	}
	
}
