package com.nuvu.test.business.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuvu.test.application.global.EMensajeEstandar;
import com.nuvu.test.application.global.RespuestaDTO;
import com.nuvu.test.business.service.TarjetaCreditoService;
import com.nuvu.test.persistence.model.TarjetaCredito;

@RestController
@RequestMapping("/tarjetas")
public class TarjetaCreditoRest {
	
	@Autowired
	private TarjetaCreditoService tarjetaService;
	
	@GetMapping
	public List<TarjetaCredito> listar(){
		return tarjetaService.listar();
	}
	
	@PostMapping("/new")
	@SuppressWarnings("rawtypes")
	public RespuestaDTO insertar(@RequestBody TarjetaCredito tarjeta) {
		if(tarjetaService.insertar(tarjeta) == 1) {
			return new RespuestaDTO();
		} else {
			return new RespuestaDTO(EMensajeEstandar.ERROR_INSERT);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/mistarjetas"+"/{idPersona}")
	public RespuestaDTO consultarTCByPersona(@PathVariable("idPersona") Integer idPersona) {
		List<TarjetaCredito> lista = tarjetaService.listarTarjetasPersonas(idPersona);
		if(!lista.isEmpty()) {
			return new RespuestaDTO().setDatos(lista);
		} else {
			return new RespuestaDTO(EMensajeEstandar.NO_RESULTADOS);
		}
	}
	
}
