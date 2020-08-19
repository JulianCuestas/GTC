package com.nuvu.test.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "topes_tarjetas_credito")
public class TopeTarjetaCredito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tope", columnDefinition = "serial")
	private Integer idTope;
	@JoinColumn(name = "id_entidad", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
	private EntidadBancaria idEntidad;
	@Column(name = "monto_permitido", nullable = false)
	private Double montoPermitido;
	@Column(name = "interes_fijo", nullable = false)
	private Double interesFijo;
	@Column(name = "rango_ingresos", length = 20, nullable = false)
	private String rangoIngresos;
	@Column(name = "marca", length = 25, nullable = false)
	private String marca;
	
	public Integer getIdTope() {
		return idTope;
	}
	public void setIdTope(Integer idTope) {
		this.idTope = idTope;
	}
	public EntidadBancaria getIdEntidad() {
		return idEntidad;
	}
	public void setIdEntidad(EntidadBancaria idEntidad) {
		this.idEntidad = idEntidad;
	}
	public Double getMontoPermitido() {
		return montoPermitido;
	}
	public void setMontoPermitido(Double montoPermitido) {
		this.montoPermitido = montoPermitido;
	}
	public Double getInteresFijo() {
		return interesFijo;
	}
	public void setInteresFijo(Double interesFijo) {
		this.interesFijo = interesFijo;
	}
	public String getRangoIngresos() {
		return rangoIngresos;
	}
	public void setRangoIngresos(String rangoIngresos) {
		this.rangoIngresos = rangoIngresos;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
}
