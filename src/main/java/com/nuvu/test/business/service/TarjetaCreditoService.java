package com.nuvu.test.business.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvu.test.business.constants.Constantes;
import com.nuvu.test.persistence.model.TarjetaCredito;
import com.nuvu.test.persistence.repo.ITarjetaCreditoRepo;

@Service
public class TarjetaCreditoService {

	@Autowired
	private ITarjetaCreditoRepo tarjetaRepo;
	
	public List<TarjetaCredito> listar() {
		List<TarjetaCredito> lista = tarjetaRepo.findAll();
		return getListaTCEnmascarada(lista);
	}
	
	public List<TarjetaCredito> listarTarjetasPersonas(Integer idPersona) {
		List<TarjetaCredito> lista = tarjetaRepo.listarTarjetasPersona(idPersona);
		return getListaTCEnmascarada(lista);
	}
	
	private List<TarjetaCredito> getListaTCEnmascarada (List<TarjetaCredito> lista) {
		List<TarjetaCredito> listaResponse = new ArrayList<TarjetaCredito>();
		if(!lista.isEmpty()) {
			Iterator<TarjetaCredito> it = lista.iterator();
			while(it.hasNext()) {
				TarjetaCredito tarjeta = it.next();
				tarjeta.setNumeroTarjeta(enmaskTC(tarjeta.getNumeroTarjeta()));
				
				listaResponse.add(tarjeta);
			}
		}
		return listaResponse;
	}
	
	public int insertar(TarjetaCredito tarjeta) {
		String numeroTarjeta = "";
		do {
			if(tarjeta.getIdTope().getMarca().equals("VISA")) {
				numeroTarjeta = generarNumeroTarjeta(Constantes.EstandarTarjetaCredito.IDENT_VISA);
			} else if (tarjeta.getIdTope().getMarca().equals("mastercard")) {
				numeroTarjeta = generarNumeroTarjeta(Constantes.EstandarTarjetaCredito.IDENT_MASTERCARD);
			} else if (tarjeta.getIdTope().getMarca().equals("AMERICAN")) {
				numeroTarjeta = generarNumeroTarjeta(Constantes.EstandarTarjetaCredito.IDENT_AMERICAN);
			}
		} while(tarjetaRepo.consultarTarjetaByNumeroTarjeta(numeroTarjeta) != null);
		
		tarjeta.setCupoActual(tarjeta.getCupoTotal());
		tarjeta.setNumeroTarjeta(numeroTarjeta);
		tarjeta.setCodigoSeguridad(generarCodigoSeguridad());
		Calendar fechaActual = Calendar.getInstance();
		tarjeta.setFechaEmision(fechaActual.getTime());
		fechaActual.add(Calendar.YEAR, 5);//Se suman 5 años de vigencia a la TC
		tarjeta.setFechaCaducidad(fechaActual.getTime());
		tarjeta.setEstado("ACTIVA");
		tarjetaRepo.save(tarjeta);
		return 1;
	}
	
	/**
	 * Genera número aleatorio de 16 caracteres incluyendo el identificador de la TC
	 * @param identTC
	 * @return
	 */
	private String generarNumeroTarjeta(String identTC) {
		return identTC+""+(long)(1000000000000000L * Math.random());
	}
	
	/**
	 * Genera código de seguridad aleatorio de tres dígitos 
	 * @return
	 */
	private String generarCodigoSeguridad() {
		return (int)(1000 * Math.random())+"";
	}
	
	/**
	 * Enmascara el número de la TC 0000XXXXXXXX0000
	 * @param numero
	 * @return
	 */
	private String enmaskTC(String numero) {
		StringBuilder mask = new StringBuilder(numero);
		int count = 4;
		while(count < 12) {
			mask.setCharAt(count, 'X');
			count++;
		}
		return mask.toString();
	}
	
}
