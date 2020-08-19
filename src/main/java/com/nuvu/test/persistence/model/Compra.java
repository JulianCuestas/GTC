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
import javax.persistence.Table;

@Entity
@Table(name = "compras")
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer idCompra;
	@JoinColumn(name = "id_persona", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Persona idPersona;
	@JoinColumn(name = "id_tc", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private TarjetaCredito idTarjetaCredito;
	@Column(name = "establecimiento", length = 255, nullable = false)
	private String establecimiento;
	@Column(name = "valor_compra", nullable = false)
	private Double valorCompra;
	@Column(name = "numero_cuotas", nullable = false)
	private Integer numeroCuotas;
	@Column(name = "fecha_compra", nullable = false)
	private Date fechaCompra;
	
	public Integer getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(Integer idCompra) {
		this.idCompra = idCompra;
	}
	public Persona getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Persona idPersona) {
		this.idPersona = idPersona;
	}
	public TarjetaCredito getIdTarjetaCredito() {
		return idTarjetaCredito;
	}
	public void setIdTarjetaCredito(TarjetaCredito idTarjetaCredito) {
		this.idTarjetaCredito = idTarjetaCredito;
	}
	public String getEstablecimiento() {
		return establecimiento;
	}
	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}
	public Double getValorCompra() {
		return valorCompra;
	}
	public void setValorCompra(Double valorCompra) {
		this.valorCompra = valorCompra;
	}
	public Integer getNumeroCuotas() {
		return numeroCuotas;
	}
	public void setNumeroCuotas(Integer numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
	}
	public Date getFecha_compra() {
		return fechaCompra;
	}
	public void setFecha_compra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	
}
