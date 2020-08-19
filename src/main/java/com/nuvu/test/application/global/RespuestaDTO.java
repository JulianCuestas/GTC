package com.nuvu.test.application.global;

import java.util.List;

/**
 * Clase genérica mensajes app. 
 * @author jcuestas
 * @param <T>
 */
public class RespuestaDTO<T> {

	private int codigo;
	private String mensaje;
	private T datos;
	private boolean status;

	public RespuestaDTO() {
		this(EMensajeEstandar.OK);
	}

	public RespuestaDTO(IGenericoMensaje iMensaje) {
		setRespuesta(iMensaje);
	}

	public RespuestaDTO(int codigo, String mensaje, boolean status) {
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.status = status;
	}

	private void setRespuesta(IGenericoMensaje iMensaje) {
		this.codigo = iMensaje.getCodigo();
		this.mensaje = iMensaje.getMensaje();
		this.status = iMensaje.isStatus();
	}

	public int getCodigo() {
		return codigo;
	}

	public RespuestaDTO<T> setCodigo(int codigo) {
		this.codigo = codigo;
		return this;
	}

	public String getMensaje() {
		return mensaje;
	}

	public RespuestaDTO<T> setMensaje(String mensaje) {
		this.mensaje = mensaje;
		return this;
	}

	public T getDatos() {
		return datos;
	}
	
	@SuppressWarnings("rawtypes")
	public RespuestaDTO<T> setDatos(T datos) {
		this.datos = datos;
		if (datos instanceof List) {
			validarLista((List) datos);
		}
		return this;
	}

	public boolean isStatus() {
		return status;
	}

	public RespuestaDTO<T> setStatus(boolean status) {
		this.status = status;
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	private void validarLista(List datos) {
		if (datos == null || datos.isEmpty()) {
			setRespuesta(EMensajeEstandar.NO_RESULTADOS);
			this.datos = null;
		}
	}
}
