package com.nuvu.test.business.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuvu.test.application.global.EMensajeEstandar;
import com.nuvu.test.application.global.RespuestaDTO;
import com.nuvu.test.business.service.TopeTarjetaCreditoService;
import com.nuvu.test.persistence.model.TopeTarjetaCredito;

@RestController
@RequestMapping("/topes")
public class TopeTarjetaCreditoRest {
	
	@Autowired
	private TopeTarjetaCreditoService topeTCService;
	
	@GetMapping
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RespuestaDTO listar(){
		List<TopeTarjetaCredito> lista = topeTCService.listar();
		if(!lista.isEmpty()) {
			return new RespuestaDTO().setDatos(lista);
		} else {
			return new RespuestaDTO(EMensajeEstandar.NO_RESULTADOS);
		}
	}
	
	@GetMapping("/mios"+"/{ingresos}")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RespuestaDTO listarTopesByCapacidad(@PathVariable("ingresos") Long ingresos){
		List<TopeTarjetaCredito> lista = topeTCService.listarTopesByCapacidad(ingresos);
		if(!lista.isEmpty()) {
			return new RespuestaDTO().setDatos(lista);
		} else {
			return new RespuestaDTO(EMensajeEstandar.NO_RESULTADOS);
		}
	}
	
}
