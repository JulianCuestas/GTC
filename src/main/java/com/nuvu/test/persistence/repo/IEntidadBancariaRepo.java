package com.nuvu.test.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nuvu.test.persistence.model.EntidadBancaria;

@Repository
public interface IEntidadBancariaRepo extends JpaRepository<EntidadBancaria, Integer> {

}
