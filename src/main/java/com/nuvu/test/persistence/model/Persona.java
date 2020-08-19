package com.nuvu.test.persistence.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "personas")
public class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer idPersona;
	@Column(name = "numero_identificacion", length = 11, nullable = false)
	private String numeroIdentificacion;
	@Column(name = "nombre_completo", length = 60, nullable = false)
	private String nombreCompleto;
	@Column(name = "ingresos", nullable = false)
	private Double ingresos;
	@JsonManagedReference
	@OneToMany(mappedBy = "idPersona", fetch = FetchType.EAGER)
	private List<TarjetaCredito> listaTarjetas;
	
	public Integer getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public Double getIngresos() {
		return ingresos;
	}
	public void setIngresos(Double ingresos) {
		this.ingresos = ingresos;
	}
	public List<TarjetaCredito> getListaTarjetas() {
		return listaTarjetas;
	}
	public void setListaTarjetas(List<TarjetaCredito> listaTarjetas) {
		this.listaTarjetas = listaTarjetas;
	}
}
