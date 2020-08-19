package com.nuvu.test.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tarjetas_credito")
public class TarjetaCredito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer idTc;
	@JoinColumn(name = "id_tope", nullable = false)
	@OneToOne(optional = false, fetch = FetchType.EAGER)
	private TopeTarjetaCredito idTope;
	@Column(name = "numero_tarjeta", length = 16, nullable = false)
	private String numeroTarjeta;
	@Column(name = "codigo_seguridad", length = 3, nullable = false)
	private String codigoSeguridad;
	@Column(name = "cupo_total", nullable = false)
	private Double cupoTotal;
	@Column(name = "cupo_actual", nullable = false)
	private Double cupoActual;
	@Column(name = "fecha_emision", nullable = false)
	private Date fechaEmision;
	@Column(name = "fecha_caducidad", nullable = false)
	private Date fechaCaducidad;
	@Column(name = "interes", nullable = false)
	private Float interes;
	@Column(name = "chip", nullable = false)
	private Boolean chip;
	@Column(name = "estado", length = 15, nullable = false)
	private String estado;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_persona", insertable = false, updatable = false, nullable = false)
	private Persona idPersona;
	
	public Integer getIdTc() {
		return idTc;
	}
	public void setIdTc(Integer idTc) {
		this.idTc = idTc;
	}
	public TopeTarjetaCredito getIdTope() {
		return idTope;
	}
	public void setIdTope(TopeTarjetaCredito idTope) {
		this.idTope = idTope;
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public String getCodigoSeguridad() {
		return codigoSeguridad;
	}
	public void setCodigoSeguridad(String codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}
	public Double getCupoTotal() {
		return cupoTotal;
	}
	public void setCupoTotal(Double cupoTotal) {
		this.cupoTotal = cupoTotal;
	}
	public Double getCupoActual() {
		return cupoActual;
	}
	public void setCupoActual(Double cupoActual) {
		this.cupoActual = cupoActual;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	public Float getInteres() {
		return interes;
	}
	public void setInteres(Float interes) {
		this.interes = interes;
	}
	public Boolean getChip() {
		return chip;
	}
	public void setChip(Boolean chip) {
		this.chip = chip;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Persona getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Persona idPersona) {
		this.idPersona = idPersona;
	}
	
}
