package com.nuvu.test.business.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvu.test.persistence.model.TopeTarjetaCredito;
import com.nuvu.test.persistence.repo.ITopeTarjetaCreditoRepo;

@Service
public class TopeTarjetaCreditoService {
	
	@Autowired
	private ITopeTarjetaCreditoRepo topeTCRepo;
	
	public List<TopeTarjetaCredito> listar(){
		return topeTCRepo.findAll();
	}
	
	public List<TopeTarjetaCredito> listarTopesByCapacidad(long ingresos){
		List<TopeTarjetaCredito> allTopes = topeTCRepo.findAll();
		List<TopeTarjetaCredito> misTopes = new ArrayList<TopeTarjetaCredito>();
		
		Iterator<TopeTarjetaCredito> it = allTopes.iterator();
		while(it.hasNext()) {
			TopeTarjetaCredito tope = it.next();
			String[] rangoIngresos = tope.getRangoIngresos().split("-");
			if((Long.parseLong(rangoIngresos[0]) <= ingresos) && (Long.parseLong(rangoIngresos[1]) >= ingresos)) {
				misTopes.add(tope);
			}
		}
		
		return misTopes;
	}
	
}
