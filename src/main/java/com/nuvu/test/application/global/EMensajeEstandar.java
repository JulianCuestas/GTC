package com.nuvu.test.application.global;

public enum EMensajeEstandar implements IGenericoMensaje {
	
	/**
	 * CORRECTAS
	 */
	OK(1, "Petición ejecutada correctamente", true),
	
	/**
	 * INFORMATIVOS
	 */
	NO_RESULTADOS(0, "No se encontraron resultados", false),
	DUPLICADO(-1, "Registro duplicado", false),
	
	/**
	 * ERRORES
	 */
	ERROR_INSERT(-2, "Error al registrar los datos", false),
	ERROR_CONECTAR(-5, "Error al conectar con el servidor", false),
	;
	
	private int codigo;
	private String mensaje;
	private boolean status;
	
	private EMensajeEstandar(int codigo, String mensaje, boolean status) {
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.status = status;
	}

	@Override
	public int getCodigo() {
		return codigo;
	}

	@Override
	public String getMensaje() {
		return mensaje;
	}

	@Override
	public boolean isStatus() {
		return status;
	}
}
