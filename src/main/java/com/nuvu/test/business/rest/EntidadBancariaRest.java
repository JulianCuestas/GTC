package com.nuvu.test.business.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuvu.test.application.global.EMensajeEstandar;
import com.nuvu.test.application.global.RespuestaDTO;
import com.nuvu.test.business.service.EntidadBancariaService;
import com.nuvu.test.persistence.model.EntidadBancaria;

@RestController
@RequestMapping("/entidades")
public class EntidadBancariaRest {
	
	@Autowired
	private EntidadBancariaService entidadService;
	
	@GetMapping
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RespuestaDTO listar() {
		List<EntidadBancaria> lista = entidadService.listar();
		if(!lista.isEmpty()) {
			return new RespuestaDTO().setDatos(lista);
		} else {
			return new RespuestaDTO(EMensajeEstandar.NO_RESULTADOS);
		}
	}
	
}
