package com.nuvu.test.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvu.test.persistence.model.EntidadBancaria;
import com.nuvu.test.persistence.repo.IEntidadBancariaRepo;

@Service
public class EntidadBancariaService {
	
	@Autowired
	private IEntidadBancariaRepo entidadRepo;
	
	public List<EntidadBancaria> listar(){
		return entidadRepo.findAll();
	}
}
